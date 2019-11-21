package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, OLETUSKASVATUS = 5;
    private int kasvatuskoko;
    private int[] luvut;
    private int alkioidenLkm;

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Epäkelpo kapasiteetti");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Epäkelpo kasvatuskoko");
        }
        luvut = new int[kapasiteetti];
        for (int i = 0; i < luvut.length; i++) {
            luvut[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            luvut[0] = luku;
            alkioidenLkm++;
            return true;
        }

        if (!kuuluu(luku)) {
            luvut[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % luvut.length == 0) {
                int[] taulukkoOld = new int[luvut.length];
                taulukkoOld = luvut;
                kopioiTaulukko(luvut, taulukkoOld);
                luvut = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(taulukkoOld, luvut);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                return true;
            }
        }
        
        return false;
    }

    public boolean poista(int luku) {
        int indeksi = -1;
        int apu;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == luvut[i]) {
                indeksi = i;
                luvut[indeksi] = 0;
                break;
            }
        }
        if (indeksi != -1) {
            for (int j = indeksi; j < alkioidenLkm - 1; j++) {
                apu = luvut[j];
                luvut[j] = luvut[j + 1];
                luvut[j + 1] = apu;
            }
            alkioidenLkm--;
            return true;
        }

        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        
        if(alkioidenLkm == 0){
            return "{}";
        }
        
        String merkkijono = "{";
        int i = 0;
        for (; i < alkioidenLkm - 1; i++){
            merkkijono = merkkijono + luvut[i] + ", ";
        }
        merkkijono = merkkijono + luvut[i] + "}";
        return merkkijono;
    }

    public int[] toIntArray() {
        return Arrays.copyOf(luvut, alkioidenLkm);
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();

        for (int luku : a.toIntArray()){
            yhdiste.lisaa(luku);
        }
        
        for (int luku : b.toIntArray()){
            yhdiste.lisaa(luku);
        }
        
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();

        for (int luku : a.toIntArray()){
            if (b.kuuluu(luku)){
                leikkaus.lisaa(luku);
            }
        }

        return leikkaus;

    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();

        for (int luku : a.toIntArray()){
            if (!b.kuuluu(luku)){
                erotus.lisaa(luku);
            }
        }

        return erotus;
    }

}
