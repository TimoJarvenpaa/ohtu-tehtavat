package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  

    @Test
    public void searchPalauttaaPelaajanNimellaHaettaessa() {
        Player pelaaja = stats.search("Kurri");
        assertEquals("Kurri", pelaaja.getName());
    }
    
    @Test
    public void searchPalauttaaNullKunPelaajaaEiLoydy() {
        Player pelaaja = stats.search("Selanne");
        assertNull(pelaaja);
    }
    
    @Test
    public void teamListaaKaikkiJoukkueenPelaajat() {
        List<Player> joukkue = stats.team("EDM");
        assertEquals(3, joukkue.size());
        assertEquals("Semenko", joukkue.get(0).getName());
        assertEquals("Kurri", joukkue.get(1).getName());
        assertEquals("Gretzky", joukkue.get(2).getName());
    }
    
    @Test
    public void topScorersPalauttaaOikeanListan() {
        List<Player> parhaat = stats.topScorers(3);
        assertEquals(3, parhaat.size());
        assertEquals("Gretzky", parhaat.get(0).getName());
        assertEquals("Lemieux", parhaat.get(1).getName());
        assertEquals("Yzerman", parhaat.get(2).getName());
    }

}
