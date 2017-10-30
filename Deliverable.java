import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.io.*;

public class Deliverable {

    public static HashMap<String, String> elems = new HashMap<String, String>(256);
    public static Pattern p = Pattern.compile("[^\\w]");

    public static void main(String args[]) throws FileNotFoundException, IOException {
        populateElems();

        ArrayList<String> res = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        try {
            String line = br.readLine();

            while (line != null) {
                res.add(readLine(line));
                line = br.readLine();
            }
        } finally {
            br.close();
        }

        // print results
        for (String out : res) {
            System.out.print(out);
        }
    }

    public static String readLine(String line) {
        String process = p.matcher(line).replaceAll("").toUpperCase();
        StringBuilder retL1 = new StringBuilder(process.length() * 5);
        StringBuilder retL2 = new StringBuilder(process.length() * 20);

        System.out.println("Process: " + process);
        if (process.length() == 0) {
            return "";
        }

        int i = 0;
        boolean start = true;
        String elem;
        while (i < process.length()) {
            elem = null;

            if (i + 2 <= process.length()) {
                // two character hashmap
                System.out.println("2 chars: " + process.substring(i, i + 2));
                elem = elems.get(process.substring(i, i + 2));
            }

            if (elem == null) {
                // single character element
                System.out.println("1 char: " + process.substring(i, i + 1));
                elem = elems.get(process.substring(i, i + 1));

                if (!start) {
                    retL1.append(" - ");
                    retL2.append(" - ");
                } else {
                    start = false;
                }
                retL1.append(process.substring(i, i + 1));
                retL2.append(elem);
                i += 1;
            } else {
                // two character string found
                if (!start) {
                    retL1.append(" - ");
                    retL2.append(" - ");
                } else {
                    start = false;
                }
                retL1.append(process.substring(i, i + 1).toUpperCase() + process.substring(i + 1, i + 2).toLowerCase());
                retL2.append(elem);
                i += 2;
            }

            if (elem == null) {
                // neither found, string can't be parsed
                return "Could not create name " + line + " out of elements.\n";
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
}