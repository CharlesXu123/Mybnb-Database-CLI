package main.java;

import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.nlp.filter.StopWordFilter;
import main.java.commands.Mybnb;
import picocli.CommandLine;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws IOException {
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        Collection<String> stopWords = new ArrayList<>();
        stopWords.add("the");
        stopWords.add("a");
        stopWords.add("an");
        stopWords.add("and");
        stopWords.add("that");
        stopWords.add("for");
        stopWords.add("it");
        stopWords.add("he");
        stopWords.add("she");
        frequencyAnalyzer.setStopWords(stopWords);
//        StopWordFilter filter = new StopWordFilter();
//        frequencyAnalyzer.addFilter(StopWordFilter);
        String b = """
                Speaking for the National Front for Liberation, which is affiliated with the Free Syrian Army (FSA), Capt. Naji Mustafa told Al-Monitor, “After the Russian occupation committed the brutal massacre in Jisr al-Shughur, the artillery and missile regiments of the National Front for Liberation responded immediately by targeting positions of the Assad regime forces and their supporters in the countryside of Latakia, Hama, Aleppo and Idlib."
                                
                He went on, "We sent a message to the regime and its supporters reminding them that we are always ready to fight back, and we will defend the people and protect the land.”
                                
                Syrian activists organized vigils in a number of cities including Azaz, al-Bab, Afrin and Ariha. The protesters called on the leaders of the opposition factions to avenge the victims of the Russian attack and raised banners denouncing the Astana process, whose sponsors Turkey, Russia and Iran recently held a summit in Tehran.
                                
                Opposition circles in northwestern Syria fear the escalation may be a prelude to a large-scale military operation by the government forces.
                                
                On July 19, the Turkish army sent reinforcements through the Bab al-Hawa crossing to Idlib. The convoy included tanks, armored transport vehicles, supply trucks and fuel tanks.
                                
                In Idlib over the past few days, fighters backed by Iran and Russia have reinforced their positions in areas under the control of the government forces.
                                
                On July 20, the Russian Defense Ministry announced that its forces shot down two drones near the Russian Khmeimim airbase in the Latakia countryside. No casualties or damage was recorded.
                                
                The Russian news agency Sputnik quoted a spokesperson for the Russian forces in Syria as saying that the drones came from the northeast, from a distance of two kilometers.
                                
                FSA leader Col. Mustafa Bakour told Al-Monitor, “The massacre committed by the Russian planes in the Idlib countryside is a message to Turkey after the failed Tehran summit. The idea is that Russia is determined to finish what it started in 2015 in its quest to restore all lands outside of Assad's control. Russia, in other words, will continue to target the areas where the factions are present and confront Turkish forces. The Russian massacre and the escalation on the Idlib front after the Tehran summit are evidence of the lack of consensus between Russia, Iran and Turkey.”
                                
                Muhammad al-Sukari, a researcher on Syrian affairs based in Gaziantep, told Al-Monitor, “The Turkish deployment of military reinforcements to Idlib is a clear indication that the Tehran summit did not satisfy Ankara. The Russian shelling of opposition areas and the escalation from both sides only confirm the lack of consensus. Meanwhile, warnings continue to be sent about the imminent Turkish military operation against the Syrian Democratic Forces.”
                                
                Sukari added, “The recent movements of the regime forces on the""";
        List<String> a_arr = new ArrayList<>();
        a_arr.add(b);
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(a_arr, true);

        for (WordFrequency a: wordFrequencies) {
            System.out.println(a.getWord() + " " + a.getFrequency());
        }
    }
}
