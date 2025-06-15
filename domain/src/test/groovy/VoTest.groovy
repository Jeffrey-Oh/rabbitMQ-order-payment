import com.jeffrey.domain.model.User
import com.jeffrey.domain.model.vo.CreatedAt
import com.jeffrey.domain.model.vo.Email
import com.jeffrey.domain.model.vo.UpdatedAt
import spock.lang.Specification

import java.time.LocalDateTime

class VoTest extends Specification {

    def "VO 테스트"() {
        given:
        def createdAt = new CreatedAt(LocalDateTime.now())
        def updatedAt = new UpdatedAt(LocalDateTime.now())
        def email = new Email("email@email.com")
        def user = new User(
            userId: 1L,
            username: "John Doe",
            email: email,
            passwordHash: "hashedPassword",
            createdAt: createdAt,
            updatedAt: updatedAt
        )

        expect:
        user.email instanceof Email
        user.createdAt instanceof CreatedAt
        user.updatedAt instanceof UpdatedAt
    }

    def "Email VO Error"() {
        when:
        new Email(value)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == "Invalid email: $value"

        where:
        value << [ "email", null, "email@", "email@e.c", "ema I1l@e.co" ]
    }

    def "Email VO – mixed validation"() {
        when:
        def email = new Email(value)

        then:
        noExceptionThrown()
        email.value == expected.trim().toLowerCase()

        where:
        value                   | expected
        " User@Example.com "    | "user@example.com"
        "TEST@TEST.CO.KR"       | "test@test.co.kr"
        "a.b-c_d+123@sub.dom"   | "a.b-c_d+123@sub.dom"
    }

    def "CreatedAt VO Error"() {
        when:
        new CreatedAt(LocalDateTime.now().plusDays(1))

        then:
        thrown(IllegalArgumentException)
    }

    def "UpdatedAt Vo Error"() {
        when:
        new UpdatedAt(LocalDateTime.now().plusDays(1))

        then:
        thrown(IllegalArgumentException)
    }
}
