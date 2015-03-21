/**
 * Created with IntelliJ IDEA.
 * Date: 2015.03.20.
 * Time: 22:35
 */
class KarakterGenerator {

    /*
     * Muveleti sorrend:
     *  - alapkepessegek meghatarozasa
     *  - alapkepzettsegek
     *  - faji bonuszok es kepzettsegek
     *  - harci alapertekek
     *  - pszi
     *  - mana
     *  - szintlepesek
     *  - kepessegpontok
     *  - felszereles
     *  - harci konfiguraciok
     *  - zseton
     */

    def rand = new Random()

    // ATTRIBUTES
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

    def harciErtekek = [alapEp: 0, ep: 0,
                        alapFp: 0, fp: 0, fpPerSzint: '',
                        alapKE: 0, alapTE: 0, alapVE: 0, alapCE: 0,
                        hmPerSzint: 0, hmTEVEKotelezo: 0]

    int alapKP = 0
    int kpPerSzint = 0

    int alapPszi = 0
    int tmeAsztral = 0

    // METHODS

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

        harciErtekek.alapCE = 20

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

        harciErtekek.alapCE = 10

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

        this
    }

    def KarakterGenerator harcos() {
        kaszt = 'harcos'
        harciErtekek.alapEp = 7
        harciErtekek.alapFp = 6
        harciErtekek.fpPerSzint = "k6()+4"

        if (kepessegek.ero == 0) {
            kepessegek.ero += k6() + 12
            kepessegek.ero = kf(kepessegek.ero)
            kepessegek.gyorsasag += k6() + k6() + 6
            kepessegek.gyorsasag = kf(kepessegek.gyorsasag)
        }

        this
    }

    def KarakterGenerator varazslo() {
        kaszt = 'varázsló'
        harciErtekek.alapEp = 3
        harciErtekek.alapFp = 2
        harciErtekek.fpPerSzint = "k6()"

        harciErtekek.alapKE = 2
        harciErtekek.alapTE = 15
        harciErtekek.alapVE = 70
        harciErtekek.alapCE = 0

        harciErtekek.hmPerSzint = 4
        harciErtekek.hmTEVEKotelezo = 1

        alapKP = 7
        kpPerSzint = 7

        // kepessegek
        if (kepessegek.ero == 0) {
            kepessegek.ero += k6x3()
            kepessegek.allokepesseg += k6x3()
            kepessegek.gyorsasag += k6x3_2x()
            kepessegek.ugyesseg += k6x3_2x()
            kepessegek.egesszeg += k6x3_2x()
            kepessegek.szepseg += k6x3()
            kepessegek.intelligencia += k6() + 12
            kepessegek.intelligencia = kf(kepessegek.intelligencia)
            kepessegek.akaratero += k6() + 12
            kepessegek.akaratero = kf(kepessegek.akaratero)
            kepessegek.asztral += k6() + 12
            kepessegek.asztral = kf(kepessegek.asztral)
        }

        // bonuszok
        bonuszok()

        this
    }

    def bonuszok() {
        harciErtekek.fp = harciErtekek.alapFp + tizFolottiBonusz(kepessegek.allokepesseg) + tizFolottiBonusz(kepessegek.akaratero)
        harciErtekek.ep = harciErtekek.alapEp + tizFolottiBonusz(kepessegek.egesszeg)
        alapPszi = tizFolottiBonusz(kepessegek.intelligencia)
        tmeAsztral = tizFolottiBonusz(kepessegek.asztral)
    }

    def int keBonusz() {
        tizFolottiBonusz(kepessegek.gyorsasag)
    }

    def int teBonusz() {
        tizFolottiBonusz(kepessegek.ero) + tizFolottiBonusz(kepessegek.gyorsasag) + tizFolottiBonusz(kepessegek.ugyesseg)
    }

    def int veBonusz() {
        tizFolottiBonusz(kepessegek.gyorsasag) + tizFolottiBonusz(kepessegek.ugyesseg)
    }

    def int ceBonusz() {
        tizFolottiBonusz(kepessegek.ugyesseg)
    }

    def int alapKPInt() {
        tizFolottiBonusz(kepessegek.intelligencia)
    }

    def int alapKPUgy() {
        tizFolottiBonusz(kepessegek.ugyesseg)
    }

    def int tizFolottiBonusz(int kepesseg) {
        Math.max(kepesseg - 10, 0)
    }

    def int k6x3_2x() {
        Math.max(k6x3(), k6x3())
    }

    def int k6x3() {
        k6() + k6() + k6()
    }

    def KarakterGenerator szintlepes() {
        harciErtekek.fp += Eval.x(this, "x." + harciErtekek.fpPerSzint)

        this
    }

}
