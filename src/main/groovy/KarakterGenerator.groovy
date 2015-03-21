/**
 * Created with IntelliJ IDEA.
 * Date: 2015.03.20.
 * Time: 22:35
 */
class KarakterGenerator {

    def kepessegek = [ero          : 0, allokepesseg: 0,
                      gyorsasag    : 0, ugyesseg: 0,
                      egesszeg     : 0, szepseg: 0,
                      intelligencia: 0, akaratero: 0, asztral: 0]

    def kulonlegesKepessegek = []

    def kepzettsegek = []

    def felszereles = []

    def penz = []

    String faj = null
    String kaszt = null

    int ep = 0
    int fp = 0
    String fpPerSzint = null

    int alapKE = 0
    int alapTE = 0
    int alapVE = 0
    int alapCE = 0

    def rand = new Random()

    def int kocka(int oldal) {
        rand.nextInt(oldal) + 1
    }

    def int k6() {
        kocka(6)
    }

    def int k10() {
        kocka(10)
    }

    def int kf(int k) {
        if (k == 18) {
            def val = kocka(100)
            if (val == 1) {
                return 0
            } else if (val < 21) {
                return k - k6()
            } else if (val < 51) {
                return 17
            } else if (val < 76) {
                return k
            } else if (val < 96) {
                return 19
            } else {
                return 20
            }
        } else {
            return k
        }
    }

    def KarakterGenerator elf() {
        faj = 'elf'
        kepessegek.ero -= 2
        kepessegek.allokepesseg -= 1
        kepessegek.gyorsasag += 1
        kepessegek.ugyesseg += 1
        kepessegek.szepseg += 1

        kulonlegesKepessegek += 'hallás 2x'
        kulonlegesKepessegek += 'látás 2.5x'
        kulonlegesKepessegek += 'infralátás 50 láb'
        kulonlegesKepessegek += 'nekromancia -8'

        kepzettsegek += 'Lovaglás (Mf)'
        kepzettsegek += 'Idomítás (Mf)'
        kepzettsegek += 'Erdőjárás (Mf)'

        alapCE = 20

        this
    }

    def KarakterGenerator felelf() {
        faj = 'félelf'
        kepessegek.ero -= 1
        kepessegek.gyorsasag += 1

        kulonlegesKepessegek += 'hallás 1.5x'
        kulonlegesKepessegek += 'látás 2x'
        kulonlegesKepessegek += 'infralátás 10 láb'
        kulonlegesKepessegek += 'nekromancia -6'

        kepzettsegek += 'Lovaglás (Mf)'
        kepzettsegek += 'Idomítás (Mf)'

        alapCE = 10

        this
    }

    def KarakterGenerator torpe() {
        faj = 'törpe'
        kepessegek.ero += 1
        kepessegek.egesszeg += 1
        kepessegek.allokepesseg += 1
        kepessegek.intelligencia -= 1
        kepessegek.szepseg -= 2
        kepessegek.asztral -= 1

        kulonlegesKepessegek += 'infralátás 30 láb'
        kulonlegesKepessegek += 'sötétben is kiváló időérzék'
        kulonlegesKepessegek += 'mélységérzékelés 2 láb pontosan'
        kulonlegesKepessegek += 'lejtésérzékelés'
        kulonlegesKepessegek += 'épület korának becslése 5 év pontosan'

        kepzettsegek += 'Csapdafelfedezés: 35%'
        kepzettsegek += 'Titkosajtó keresés: 30%'

        alapCE = 20

        this
    }

    def KarakterGenerator ork() {
        faj = 'udvari ork'
        kepessegek.ero += 2
        kepessegek.egesszeg += 2
        kepessegek.allokepesseg += 1
        kepessegek.intelligencia -= 1
        kepessegek.szepseg -= 3
        kepessegek.asztral -= 3

        kulonlegesKepessegek += 'infralátás 15 láb'
        kulonlegesKepessegek += 'mélységérzékelés 12 láb pontosan'
        kulonlegesKepessegek += 'lejtésérzékelés'
        kulonlegesKepessegek += 'szaglás 5x '

        kepzettsegek += 'Csapdafelfedezés: 20%'
        kepzettsegek += 'Titkosajtó keresés: 10%'

        alapCE = 20

        this
    }

    def KarakterGenerator harcos() {
        kaszt = 'harcos'
        ep = 7
        fp = 6
        fpPerSzint = "k6()+4"

        kepessegek.ero += k6() + 12
        kepessegek.ero = kf(kepessegek.ero)
    }

    def KarakterGenerator varazslo() {
        kaszt = 'varázsló'
        ep = 3
        fp = 2
        fpPerSzint = "k6()"

        this
    }

    def KarakterGenerator szintlepes() {
        fp += Eval.x(this, "x." + fpPerSzint)

        this
    }

}
