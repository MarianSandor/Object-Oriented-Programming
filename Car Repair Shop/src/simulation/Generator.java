/** Generator class
 *
 * -provides the useful methods to generate random number plates, phone numbers or names.
 */

package simulation;

import java.util.Random;

public class Generator {

    public static String numberPlate() {
        String regions[] = { "B", "AB", "AG", "AR", "BC", "BH", "BN", "BR", "BT", "BV", "BZ", "CJ", "CL", "CS", "CT", "CV", "DB",
                                "DJ", "GJ", "GL", "GR", "HD", "HR", "IF", "IL",  "IS", "MH", "MM", "MS", "NT", "OT", "PH", "SB",
                                "SJ", "SM", "SV", "TL", "TM", "TR", "VL", "VN", "VS"};
        String numbers[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        String letters[] = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L",
                                "Z", "X", "C", "V", "B", "N", "M" };

        Random random = new Random();
        String plate = "";

        plate += regions[random.nextInt(regions.length)];
        plate += " ";

        plate += numbers[1 + random.nextInt(numbers.length - 1)];
        plate += numbers[random.nextInt(numbers.length)];
        plate += " ";

        plate += letters[random.nextInt(letters.length)];
        plate += letters[random.nextInt(letters.length)];
        plate += letters[random.nextInt(letters.length)];

        return plate;
    }

    public static String phoneNumber() {
        String numbers[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        Random random = new Random();
        String phone = "07";

        for (int i = 0; i < 8; i++) {
            phone += numbers[random.nextInt(numbers.length)];
        }

        return phone;
    }

    public static String name() {
        String names[] = {"Popescu", "Ionescu", "Popa", "Pop", "Niță", "Nițu", "Constantinescu", "Stan", "Stanciu", "Dumitrescu",
                            "Dima", "Gheorghiu", "Ioniță", "Marin", "Tudor", "Dobre", "Barbu", "Nistor", "Florea", "Frățilă",
                            "Dinu", "Dinescu", "Georgescu", "Stoica", "Diaconu", "Diaconescu", "Mocanu", "Voinea", "Albu",
                            "Petrescu", "Manole", "Cristea", "Toma", "Stănescu", "Pușcașu", "Tomescu", "Sava", "Ciobanu",
                            "Rusu", "Ursu", "Lupu", "Munteanu", "Mehedințu", "Andreescu", "Sava", "Mihăilescu", "Iancu",
                            "Blaga", "Teodoru", "Teodorescu", "Moise", "Moisescu", "Călinescu", "Tabacu", "Negoiță", "Ifrim" };
        Random random = new Random();

        return names[random.nextInt(names.length)];
    }
}
