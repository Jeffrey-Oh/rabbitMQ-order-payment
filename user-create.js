import http from 'k6/http';
import { check } from 'k6';

export const options = {
    vus: 1,
    iterations: 1,
};

const port = 18080;

export default function () {
    for (let i = 51; i <= 500; i++) {
        const payload = JSON.stringify({
            username: `name${i}`,
            email: `email${i}@test.com`,
            password: `test${i}`
        });

        const headers = { 'Content-Type': 'application/json' };

        const res = http.post(`http://localhost:${port}/api/users`, payload, { headers });

        check(res, {
            [`user${i} created`]: (r) => r.status === 201
        });

        console.log(`Created user${i} - status: ${res.status}`);
    }
}
