package nl.hva.gravatar;

/**
 * @author Gravatar
 *
 * See http://en.gravatar.com/ for open-source implementations
 */
public enum GravatarDefaultImage {

    GRAVATAR_ICON(""),
    IDENTICON("identicon"),
    MONSTERID("monsterid"),
    WAVATAR("wavatar"),
    HTTP_404("404"),
    MYSTERY_MAN("mm"),
    RETRO("retro"),
    BLANK("blank");

    private String code;

    private GravatarDefaultImage(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
