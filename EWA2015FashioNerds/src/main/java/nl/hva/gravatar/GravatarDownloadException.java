package nl.hva.gravatar;

/**
 * @author Gravatar
 *
 * See http://en.gravatar.com/ for open-source implementations
 */
public class GravatarDownloadException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GravatarDownloadException(Throwable cause) {
		super("Gravatar could not be downloaded: " + cause.getMessage(), cause);
	}

}
