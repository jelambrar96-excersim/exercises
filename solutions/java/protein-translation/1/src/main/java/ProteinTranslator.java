import java.lang.IllegalArgumentException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;


class ProteinTranslator {

    String translateCodon(String codon) {
        switch (codon) {
            case "AUG":
                return "Methionine";
            case "UUU":
            case "UUC":
                return "Phenylalanine";
            case "UUA":
            case "UUG":
                return "Leucine";
            case "UCU":
            case "UCC":
            case "UCA":
            case "UCG":
                return "Serine";
            case "UAU":
            case "UAC":
                return "Tyrosine";
            case "UGU":
            case "UGC":
                return "Cysteine";
            case "UGG":
                return "Tryptophan";
            case "UAA":
            case "UAG":
            case "UGA":
                return "STOP";
            default:
                throw new IllegalArgumentException("Invalid codon");
        }
    }

    List<String> translate(String rnaSequence) {
        int rnaSize = rnaSequence.length();
        List<String> listAmino = new ArrayList<String>(rnaSize / 3);
        for(int i = 0; i < rnaSize; i+= 3) {
            if (rnaSize <= i + 2) {
                throw new IllegalArgumentException("Invalid codon");
            }
            String codon = rnaSequence.substring(i, i + 3);
            String amino = translateCodon(codon);
            if (amino == "STOP") {
                break;
            }
            listAmino.add(amino);
        }
        return listAmino;
    }

}
