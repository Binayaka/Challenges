package challenges.binayaka.trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import challenges.binayaka.common.InstansceKeeper;

/**
 * This will build the grammar for our L-Systems
 * 
 * @author Binayaka
 *
 */
public class GrammerBuilder {
	public LinkedList<Rule> rules = new LinkedList<>();
	public Map<Character, TurtleRule> turtleRules = new HashMap<>();
	private String rootString;
	private TurtleRule initRule = null;
	private int generationCount = 0;
	private TurtleRule endRule;

	private GrammerBuilder(String axiom) {
		rootString = axiom;
	}

	/**
	 * This will return an instance of the grammar builder
	 * 
	 * @return
	 */
	public static GrammerBuilder getBuilder(String axiom) {
		return new GrammerBuilder(axiom);
	}

	/**
	 * This will add a new rule to the grammar
	 * 
	 * @param variable
	 * @param replacement
	 * @return
	 */
	public GrammerBuilder addRule(String variable, String replacement) {
		Rule newRule = new Rule(variable, replacement);
		rules.add(newRule);
		return this;
	}

	public GrammerBuilder withTurtleRule(String variable, TurtleRule rule) {
		rule.setGrammar(this);
		turtleRules.put(variable.charAt(0), rule);
		return this;
	}

	public TurtleRule getInitRule() {
		return initRule;
	}

	public TurtleRule getEndRule() {
		return endRule;
	}

	public GrammerBuilder withTurtleInitRule(TurtleRule initRule) {
		initRule.setGrammar(this);
		this.initRule = initRule;
		return this;
	}

	public GrammerBuilder withTurtleEndRule(TurtleRule endRule) {
		endRule.setGrammar(this);
		this.endRule = endRule;
		return this;
	}

	public int getGenerationCount() {
		return generationCount;
	}

	/**
	 * This will return the generated string
	 * 
	 * @return
	 */
	public String getRootString() {
		return rootString;
	}

	/**
	 * This will generate the next generation string
	 */
	public void generate() {
		generationCount++;
		int newLength = (int) (TurtleRule.getLineLength() * TurtleRule.getDECREMENT_RATIO());
		TurtleRule.setLineLength(newLength);
		StringBuilder nextSentence = new StringBuilder();
		for (int i = 0; i < rootString.length(); i++) {
			char current = rootString.charAt(i);
			boolean found = false;
			for (int j = 0; j < rules.size(); j++) {
				if (current == rules.get(j).getAxiom()) {
					found = true;
					nextSentence.append(rules.get(j).getReplacement());
				}
			}
			if (!found) {
				nextSentence.append(current);
			}
		}
		rootString = nextSentence.toString();
	}

	/**
	 * This will execute all registered turtle rules
	 */
	public void executeTurtleRules() {
		Turtle turtle = (Turtle) InstansceKeeper.getInstance(Turtle.class);
		if (initRule == null) {
			turtle.init();
		} else {
			initRule.execute();
		}
		for (int i = 0; i < rootString.length(); i++) {
			char current = rootString.charAt(i);
			if (turtleRules.get(current) == null) {
				// we don't have any rules for this character
				continue;
			}
			turtleRules.get(current).execute();
		}
		if (endRule != null) {
			endRule.execute();
		}
	}
}
