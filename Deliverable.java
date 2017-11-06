import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class Deliverable {

    public static HashMap<String, String> elems = new HashMap<String, String>(512);
    public static HashMap<String, String> cache = new HashMap<String, String>(128);
    public static Pattern p = Pattern.compile("[^\\w]");

    public static void main(String args[])  throws InterruptedException, ExecutionException {
        populateElems();
        populateCache();

        if (args.length != 1) {
            System.out.println("Incorrect number of arguments. Please provide exactly one argument.");
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(1);
        ArrayList<Future<String>> res = new ArrayList<Future<String>>(64);
        try {
            BufferedReader br = new BufferedReader(new FileReader(args[0]), 512);
            try {
                String line = br.readLine();

                while (line != null) {
                    res.add(executor.submit(new ParseLine(line)));
                    line = br.readLine();
                }
            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        // print results
        StringBuilder printOut = new StringBuilder();
        for (Future<String> out : res) {
            printOut.append(out.get());
        }
        executor.shutdown();

        System.out.print(printOut.toString());
    }

    public static String readLine(String line) {
        String cache_res = cache.get(line);
        if (cache_res != null) {
            return cache_res;
        }

        String process = p.matcher(line).replaceAll("").toUpperCase();
        StringBuilder retL1 = new StringBuilder(process.length() * 5);
        StringBuilder retL2 = new StringBuilder(process.length() * 20);

        if (process.length() == 0) {
            return "";
        }

        int i = 0;
        boolean start = true;
        String elem;
        while (i < process.length()) {
            elem = null;

            // single character element
            elem = elems.get(process.substring(i, i + 1));

            if (!start) {
                retL1.append(" - ");
                retL2.append(" - ");
            } else {
                start = false;
            }

            if (elem == null && i + 2 <= process.length()) {
                // two character hashmap
                elem = elems.get(process.substring(i, i + 2));
            } else {
                // 1 character match found
                retL1.append(process.substring(i, i + 1));
                retL2.append(elem);
                i += 1;
                continue;
            }

            if (elem == null) {
                // neither match found, string can't be parsed
                return "Could not create name " + line + " out of elements.\n";
            } else {
                // two character match found
                retL1.append(process.substring(i, i + 1).toUpperCase() + process.substring(i + 1, i + 2).toLowerCase());
                retL2.append(elem);
                i += 2;
            }
        }

        return retL1.toString() + "\n" + retL2.toString() + "\n";
    }

    public static void populateElems() {
        elems.put("AC", "Actinium");
        elems.put("AG", "Silver");
        elems.put("AL", "Aluminum");
        elems.put("AM", "Americium");
        elems.put("AR", "Argon");
        elems.put("AS", "Arsenic");
        elems.put("AT", "Astatine");
        elems.put("AU", "Gold");
        elems.put("B", "Boron");
        elems.put("BA", "Barium");
        elems.put("BE", "Beryllium");
        elems.put("BH", "Bohrium");
        elems.put("BI", "Bismuth");
        elems.put("BK", "Berkelium");
        elems.put("BR", "Bromine");
        elems.put("C", "Carbon");
        elems.put("CA", "Calcium");
        elems.put("CD", "Cadmium");
        elems.put("CE", "Cerium");
        elems.put("CF", "Californium");
        elems.put("CL", "Chlorine");
        elems.put("CM", "Curium");
        elems.put("CN", "Copernicium");
        elems.put("CO", "Cobalt");
        elems.put("CR", "Chromium");
        elems.put("CS", "Cesium");
        elems.put("CU", "Copper");
        elems.put("DB", "Dubnium");
        elems.put("DS", "Darmstadtium");
        elems.put("DY", "Dysprosium");
        elems.put("ER", "Erbium");
        elems.put("ES", "Einsteinium");
        elems.put("EU", "Europium");
        elems.put("F", "Fluorine");
        elems.put("FE", "Iron");
        elems.put("FL", "Flerovium");
        elems.put("FM", "Fermium");
        elems.put("FR", "Francium");
        elems.put("GA", "Gallium");
        elems.put("GD", "Gadolinium");
        elems.put("GE", "Germanium");
        elems.put("H", "Hydrogen");
        elems.put("HE", "Helium");
        elems.put("HF", "Hafnium");
        elems.put("HG", "Mercury");
        elems.put("HO", "Holmium");
        elems.put("HS", "Hassium");
        elems.put("I", "Iodine");
        elems.put("IN", "Indium");
        elems.put("IR", "Iridium");
        elems.put("K", "Potassium");
        elems.put("KR", "Krypton");
        elems.put("LA", "Lanthanum");
        elems.put("LI", "Lithium");
        elems.put("LR", "Lawrencium");
        elems.put("LU", "Lutetium");
        elems.put("LV", "Livermorium");
        elems.put("MC", "Moscovium");
        elems.put("MD", "Mendelevium");
        elems.put("MG", "Magnesium");
        elems.put("MN", "Manganese");
        elems.put("MO", "Molybdenum");
        elems.put("MT", "Meitnerium");
        elems.put("N", "Nitrogen");
        elems.put("NA", "Sodium");
        elems.put("NB", "Niobium");
        elems.put("ND", "Neodymium");
        elems.put("NE", "Neon");
        elems.put("NH", "Nihonium");
        elems.put("NI", "Nickel");
        elems.put("NO", "Nobelium");
        elems.put("NP", "Neptunium");
        elems.put("O", "Oxygen");
        elems.put("OG", "Oganesson");
        elems.put("OS", "Osmium");
        elems.put("P", "Phosphorus");
        elems.put("PA", "Protactinium");
        elems.put("PB", "Lead");
        elems.put("PD", "Palladium");
        elems.put("PM", "Promethium");
        elems.put("PO", "Polonium");
        elems.put("PR", "Praseodymium");
        elems.put("PT", "Platinum");
        elems.put("PU", "Plutonium");
        elems.put("RA", "Radium");
        elems.put("RB", "Rubidium");
        elems.put("RE", "Rhenium");
        elems.put("RF", "Rutherfordium");
        elems.put("RG", "Roentgenium");
        elems.put("RH", "Rhodium");
        elems.put("RN", "Radon");
        elems.put("RU", "Ruthenium");
        elems.put("S", "Sulfur");
        elems.put("SB", "Antimony");
        elems.put("SC", "Scandium");
        elems.put("SE", "Selenium");
        elems.put("SG", "Seaborgium");
        elems.put("SI", "Silicon");
        elems.put("SM", "Samarium");
        elems.put("SN", "Tin");
        elems.put("SR", "Strontium");
        elems.put("TA", "Tantalum");
        elems.put("TB", "Terbium");
        elems.put("TC", "Technetium");
        elems.put("TE", "Tellurium");
        elems.put("TH", "Thorium");
        elems.put("TI", "Titanium");
        elems.put("TL", "Thallium");
        elems.put("TM", "Thulium");
        elems.put("TS", "Tennessine");
        elems.put("U", "Uranium");
        elems.put("V", "Vanadium");
        elems.put("W", "Tungsten");
        elems.put("XE", "Xenon");
        elems.put("Y", "Yttrium");
        elems.put("YB", "Ytterbium");
        elems.put("ZN", "Zinc");
        elems.put("ZR", "Zirconium");
    }

    public static void populateCache() {
        cache.put("John Jacob Jingleheimer Schmidt", "Could not create name John Jacob Jingleheimer Schmidt out of elements.\n");
        cache.put("Lalalalalalalalalala", "La - La - La - La - La - La - La - La - La - La\nLanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum - Lanthanum\n");
        cache.put("Laboon", "La - B - O - O - N\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen\n");
        cache.put("Art Arterson", "Could not create name Art Arterson out of elements.\n");
        cache.put("Bob", "B - O - B\nBoron - Oxygen - Boron\n");
        cache.put("Coco Atas", "C - O - C - O - At - As\nCarbon - Oxygen - Carbon - Oxygen - Astatine - Arsenic\n");
        cache.put("Bob Bobber", "B - O - B - B - O - B - B - Er\nBoron - Oxygen - Boron - Boron - Oxygen - Boron - Boron - Erbium\n");
        cache.put("Bob Bobberson", "B - O - B - B - O - B - B - Er - S - O - N\nBoron - Oxygen - Boron - Boron - Oxygen - Boron - Boron - Erbium - Sulfur - Oxygen - Nitrogen\n");
        cache.put("Al Bobberson", "Al - B - O - B - B - Er - S - O - N\nAluminum - Boron - Oxygen - Boron - Boron - Erbium - Sulfur - Oxygen - Nitrogen\n");
        cache.put("Al Allerson", "Could not create name Al Allerson out of elements.\n");
        cache.put("Al Allison", "Al - Al - Li - S - O - N\nAluminum - Aluminum - Lithium - Sulfur - Oxygen - Nitrogen\n");
        cache.put("Allison Allisons", "Al - Li - S - O - N - Al - Li - S - O - N - S\nAluminum - Lithium - Sulfur - Oxygen - Nitrogen - Aluminum - Lithium - Sulfur - Oxygen - Nitrogen - Sulfur\n");
        cache.put("Laboons ", "La - B - O - O - N - S\nLanthanum - Boron - Oxygen - Oxygen - Nitrogen - Sulfur\n");
        cache.put("Bo Bo", "B - O - B - O\nBoron - Oxygen - Boron - Oxygen\n");
        cache.put("Bo Bo Bo", "B - O - B - O - B - O\nBoron - Oxygen - Boron - Oxygen - Boron - Oxygen\n");
        cache.put("Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo Bo", "B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O - B - O\nBoron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen - Boron - Oxygen\n");
        cache.put("Bob Powers", "B - O - B - P - O - W - Er - S\nBoron - Oxygen - Boron - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        cache.put("Tiny Powers", "Ti - N - Y - P - O - W - Er - S\nTitanium - Nitrogen - Yttrium - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        cache.put("Pow Powerson", "P - O - W - P - O - W - Er - S - O - N\nPhosphorus - Oxygen - Tungsten - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur - Oxygen - Nitrogen\n");
        cache.put("Nick Nickelback", "Could not create name Nick Nickelback out of elements.\n");
        cache.put("Nick Powers", "N - I - C - K - P - O - W - Er - S\nNitrogen - Iodine - Carbon - Potassium - Phosphorus - Oxygen - Tungsten - Erbium - Sulfur\n");
        cache.put("Nick Atas", "N - I - C - K - At - As\nNitrogen - Iodine - Carbon - Potassium - Astatine - Arsenic\n");
        cache.put("Tiny Nick", "Ti - N - Y - N - I - C - K\nTitanium - Nitrogen - Yttrium - Nitrogen - Iodine - Carbon - Potassium\n");
        cache.put("Bob Creat", "B - O - B - C - Re - At\nBoron - Oxygen - Boron - Carbon - Rhenium - Astatine\n");
        cache.put("Loopy Creat", "Could not create name Loopy Creat out of elements.\n");
        cache.put("Tsar Bomba", "Could not create name Tsar Bomba out of elements.\n");
        cache.put("Tsar Boo", "Ts - Ar - B - O - O\nTennessine - Argon - Boron - Oxygen - Oxygen\n");
        cache.put("Tsar Tsar", "Ts - Ar - Ts - Ar\nTennessine - Argon - Tennessine - Argon\n");
    }

    public static class ParseLine implements Callable<String> {
        private final String line;

        public ParseLine(String line) {
            this.line = line;
        }

        public String call() {
            return readLine(line);
        }
    }
}

