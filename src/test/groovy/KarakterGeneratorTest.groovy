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

    def "kf"() {
       expect:
       new KarakterGenerator().kf(e) = uj

        where:
        e | uj
        5 | 5
        17 | 17
    }
}
