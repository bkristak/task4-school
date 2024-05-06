package atomcode.constants;

public enum Nationality {

    AUSTRIAN("Austria"),
    BELGIAN("Belgium"),
    BULGARIAN("Bulgaria"),
    CROATIAN("Croatia"),
    CYPRIOT("Cyprus"),
    CZECH("Czech Republic"),
    DANISH("Denmark"),
    ESTONIAN("Estonia"),
    FINNISH("Finland"),
    FRENCH("France"),
    GERMAN("Germany"),
    GREEK("Greece"),
    HUNGARIAN("Hungary"),
    IRISH("Ireland"),
    ITALIAN("Italy"),
    LATVIAN("Latvia"),
    LITHUANIAN("Lithuania"),
    LUXEMBOURGER("Luxembourg"),
    MALTESE("Malta"),
    DUTCH("Netherlands"),
    POLISH("Poland"),
    PORTUGUESE("Portugal"),
    ROMANIAN("Romania"),
    SLOVAK("Slovakia"),
    SLOVENIAN("Slovenia"),
    SPANISH("Spain"),
    SWEDISH("Sweden");

    private final String citizenship;
    Nationality(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getCitizenship() {return citizenship;} // todo use case

}
