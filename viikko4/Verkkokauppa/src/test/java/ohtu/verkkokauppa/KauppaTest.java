package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    String kaupanTili;
    int viitenro;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kaupanTili = "33333-44455";
        viitenro = 42;
        
        k = new Kauppa(varasto, pankki, viite);
        
        when(viite.uusi())
                .thenReturn(viitenro)
                .thenReturn(viitenro + 1)
                .thenReturn(viitenro + 2);
        
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        when(varasto.saldo(2)).thenReturn(20);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "peruna", 1));
        
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "viljaporsaan sisäfile", 7));
        
        k.aloitaAsiointi();
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(viitenro), eq("12345"), eq(kaupanTili), eq(5));
    }

    @Test
    public void kahdenEriTuotteenOstamisenLopuksiPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(viitenro), eq("12345"), eq(kaupanTili), eq(6));
    }

    @Test
    public void kahdenSamanTuotteenOstamisenLopuksiPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(viitenro), eq("12345"), eq(kaupanTili), eq(10));
    }

    @Test
    public void varastostaLoppuneenTuotteenOstamisenLopuksiPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(3);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), eq(viitenro), eq("12345"), eq(kaupanTili), eq(5));
    }
    
    @Test
    public void kauppaPyytaaUudenViitenumeronJokaiselleMaksutapahtumalle() {
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(viite, times(1)).uusi();
        verify(pankki).tilisiirto(eq("pekka"), eq(viitenro), eq("12345"), eq(kaupanTili), eq(5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("timo", "67890");
        
        verify(viite, times(2)).uusi();
        verify(pankki).tilisiirto(eq("timo"), eq(viitenro + 1), eq("67890"), eq(kaupanTili), eq(1));
    }
    
    @Test
    public void tuotteenPoistaminenOstoskoristaOtetaanHuomioonOstoksiaMaksettaessa() {
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.poistaKorista(2);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(viitenro), eq("12345"), eq(kaupanTili), eq(5));
    }
}
