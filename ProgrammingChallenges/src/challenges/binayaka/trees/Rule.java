package challenges.binayaka.trees;

/**
 * This will define an L-System rule
 * 
 * @author Binayaka
 *
 */
public class Rule {

	private final Character variable;
	private final String replacement;

	public Rule(String variable, String replacement) {
		this.variable = variable.charAt(0);
		this.replacement = replacement;
	}

	public Rule(Character variable, String replacement) {
		this.variable = variable;
		this.replacement = replacement;
	}

	public Character getAxiom() {
		return variable;
	}

	public String getReplacement() {
		return replacement;
	}

}
