import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Lets the user pick a text file with JFileChooser, splits it into words, and
 * uses a ShortWordFilter (through the Filter interface) to display only the
 * words shorter than 5 characters.
 *
 * @author Your Name
 * @version 1.0
 */
public class ShortLister
{
    /**
     * Program entry point.
     * @param args command line arguments, unused
     */
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose a text file to scan");
        chooser.setFileFilter(new FileNameExtensionFilter("Text files", "txt"));
        chooser.setCurrentDirectory(new File("."));

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
        {
            System.out.println("No file chosen.");
            return;
        }

        Path file = chooser.getSelectedFile().toPath();

        // Programmed against the Filter interface, not the concrete class,
        // so any other Filter implementation could be swapped in here.
        Filter filter = new ShortWordFilter();

        int totalWords = 0;
        ArrayList<String> shortWords = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                for (String word : tokenize(line))
                {
                    totalWords++;
                    if (filter.accept(word))
                    {
                        shortWords.add(word);
                    }
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Could not read the file: " + e.getMessage());
            return;
        }

        System.out.println("===== Short Words (length < 5) =====");
        System.out.println("Scanned " + totalWords + " word(s), found " + shortWords.size() + " short word(s):");
        for (String word : shortWords)
        {
            System.out.println("  " + word);
        }
    }

    /**
     * Splits a line of text into words on whitespace, stripping any leading
     * or trailing punctuation from each token so that, for example, "cat."
     * and "cat" are treated as the same 3-letter word.
     *
     * @param line one line of text from the file
     * @return the cleaned, non-empty word tokens found on that line
     */
    private static List<String> tokenize(String line)
    {
        List<String> tokens = new ArrayList<>();
        for (String raw : line.trim().split("\\s+"))
        {
            String cleaned = raw.replaceAll("^[^a-zA-Z0-9']+|[^a-zA-Z0-9']+$", "");
            if (!cleaned.isEmpty())
            {
                tokens.add(cleaned);
            }
        }
        return tokens;
    }
}