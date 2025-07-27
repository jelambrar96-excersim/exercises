class RnaTranscription {


    char transcribeChar(char c) {
        switch (c) {
            case 'G':
                return 'C';
            case 'C':
                return 'G';
            case 'T':
                return 'A';
            case 'A':
                return 'U'; 
            default:
                throw new IllegalArgumentException();
        }
    }


    String transcribe(String dnaStrand) {
        
        char []dnaCharArray = dnaStrand.toCharArray();
        for (int i=0; i < dnaCharArray.length; ++i) {
            dnaCharArray[i] = transcribeChar(dnaCharArray[i]);
        }
        return String.copyValueOf(dnaCharArray);

    }

}
