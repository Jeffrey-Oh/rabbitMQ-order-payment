import http from 'k6/http';
import { sleep, check } from 'k6';
import { Trend } from 'k6/metrics';

export const options = {
    stages: [
        { duration: '5s', target: 20 },
        { duration: '5s', target: 50 },
        { duration: '10s', target: 100 },
        { duration: '10s', target: 100 },
    ],
};

const loginTrend = new Trend('login_duration');
const orderTrend = new Trend('order_duration');
const pollingTrend = new Trend('polling_duration');
const port = 18080;

export default function () {
    const userIndex = __VU; // 각 VU는 1부터 시작

    // 1. 로그인
    const loginRes = http.post(`http://localhost:${port}/api/users/login`, JSON.stringify({
        username: `name${userIndex}`,
        password: `test${userIndex}`,
    }), {
        headers: { 'Content-Type': 'application/json' },
    });
    loginTrend.add(loginRes.timings.duration);

    check(loginRes, {
        [`login success`]: (res) => res.status === 200,
    });

    const accessToken = loginRes.json('accessToken');
    const authHeader = { Authorization: `Bearer ${accessToken}` };

    // 2. 주문 요청
    const orderBody = {
        items: [
            { menuId: 1, quantity: 1 },
            { menuId: 2, quantity: 2 },
            { menuId: 3, quantity: 3 },
        ],
        paymentMethod: 'CREDIT_CARD',
    };

    const orderRes = http.post(`http://localhost:${port}/api/orders`, JSON.stringify(orderBody), {
        headers: {
            'Content-Type': 'application/json',
            ...authHeader,
        },
    });
    orderTrend.add(orderRes.timings.duration);

    check(orderRes, {
        [`order success`]: (res) => res.status === 201,
    });

    const orderId = orderRes.json('orderId');

    // 3. Polling (최대 10초간 1초 간격)
    let completed = false;
    for (let i = 0; i < 10; i++) {
        const pollRes = http.get(`http://localhost:${port}/api/orders/polling/${orderId}`, {
            headers: authHeader,
        });
        pollingTrend.add(pollRes.timings.duration);

        if (pollRes.status === 200 && pollRes.body.includes('COMPLETED')) {
            completed = true;
            break;
        }

        sleep(0.3);
    }

    check(completed, {
        [`order completed within 10s`]: (v) => v === true,
    });
}
