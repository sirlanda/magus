/**
 * Created with IntelliJ IDEA.
 * Date: 2015.03.20.
 * Time: 22:32
 */
class KarakterGeneratorTest extends spock.lang.Specification {

    def "k6 1 és 6 közé dob"() {
        expect:
        new KarakterGenerator().k6() < 7
    }

    def "kf-5"() {
        setup:
        def gen = new KarakterGenerator()

        expect:
        gen.kf(5) == 5
    }

    def "kf-18-halal"() {
        setup:
        def gen = new KarakterGenerator() {
            @Override
            int kocka(int oldal) {
                return 1
            }
        }

        expect:
        gen.kf(18) == 0
    }

    def "kf-18-csokken"() {
        setup:
        def gen = new KarakterGenerator() {
            @Override
            int kocka(int oldal) {
                return 3
            }
        }

        expect:
        gen.kf(18) == 15
    }

    def "kf-18-csokken-egyet"() {
        setup:
        def gen = new KarakterGenerator() {
            @Override
            int kocka(int oldal) {
                return 21
            }
        }

        expect:
        gen.kf(18) == 17
    }

    def "kf-18-valtozatlan"() {
        setup:
        def gen = new KarakterGenerator() {
            @Override
            int kocka(int oldal) {
                return 66
            }
        }

        expect:
        gen.kf(18) == 18
    }

    def "kf-18-no19"() {
        setup:
        def gen = new KarakterGenerator() {
            @Override
            int kocka(int oldal) {
                return 90
            }
        }

        expect:
        gen.kf(18) == 19
    }

    def "kf-18-no20"() {
        setup:
        def gen = new KarakterGenerator() {
            @Override
            int kocka(int oldal) {
                return 99
            }
        }

        expect:
        gen.kf(18) == 20
    }
}
