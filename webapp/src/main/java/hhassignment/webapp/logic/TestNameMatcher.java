package hhassignment.webapp.logic;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.xdrop.fuzzywuzzy.FuzzySearch;

/**
 * Utilities class with static methods providing the logic for approximate String matching. 
 * Currently using the <code>me.xdrop.fuzzywuzzy.FuzzySearch</code> library for the String matching algorithms. 
 */
public class TestNameMatcher {
	/**
	 * Finds the best String match for <code>lookup</code> in <code>sources</code>
	 * that is above the <code>ratioThreshold</code>. Setting
	 * <code>strictMatch</code> to <code>true</code> dictates to return the match
	 * only if there was a single best match that was found above the
	 * <code>ratioThreshold</code>
	 * 
	 * @param sources        <code>List</code> of <code>Strings</code> to match
	 *                       against.
	 * @param lookup         <code>String</code> to try and match for.
	 * @param ratioThreshold <code>Double</code> indicating the ratio threshold
	 *                       below which the <code>lookup</code> won't be considered
	 *                       a match. The ratio range is 0-100 when 100 is a perfect
	 *                       match.
	 * @param strictMatch    <code>boolean</code> that dictates whether the match
	 *                       must be strictly to a single value. In case
	 *                       <code>strictMatch</code> is <code>false</code> and
	 *                       there are more than one match with the same ratio one
	 *                       of them will be returned arbitrarily.
	 * @return <code>String</code> that was found to be the best match above the
	 *         threshold. If no match was found or if more than one match was found
	 *         and <code>strictMatch</code> is <code>true</code> then
	 *         <code>null</code> will be returned.
	 */
	public static String findMatch(List<String> sources, String lookup, Double ratioThreshold, boolean strictMatch) {
		Map<String, Double> ratiosMap = mapRatios(sources, lookup);
		Collection<Double> ratios = ratiosMap.values();

		Double bestRatio = ratios.stream().max(Comparator.comparingDouble(Double::valueOf)).get();

		// Check if the ratio is above minimal threshold ratio
		if (bestRatio.compareTo(ratioThreshold) < 0) {
			return null;
		}

		// if strictMatch flag is set check if there is more than one max ratio meaning
		// there is more than one best match
		long numOfMatches = ratios.stream().filter(ratio -> ratio.equals(bestRatio)).count();
		if (strictMatch && numOfMatches > 1) {
			return null;
		}

		// Find the name of the test with the best ratio
		return ratiosMap.entrySet().stream().filter(item -> item.getValue().equals(bestRatio)).findFirst().get()
				.getKey();
	}

	/**
	 * Using the <code>List</code> of <code>sources</code> creates a <code>Map</code> for each
	 * member with its ratio for matching with <code>lookup</code>.
	 * 
	 * @param sources <code>List</code> of <code>Strings</code> to match against.
	 * @param lookup  <code>String</code> to try and match for.
	 * @return <code>{@literal Map<String, Double>}</code> representing the ratio
	 *         values using the <code>sources</code> as keys.
	 */
	public static Map<String, Double> mapRatios(List<String> sources, String lookup) {
		if (sources == null) {
			return null;
		}

		Map<String, Double> ratios = sources.stream()
				.collect(Collectors.toMap(source -> source, source -> calculateAverageRatio(source, lookup)));
		return ratios;
	}

	/**
	 * Calculates and returns the average ratio between <b>Weighted Ratio</b> and
	 * <b>Token Set Ratio</b> for better accuracy.
	 * 
	 * @param source <code>String</code> to match against.
	 * @param lookup <code>String</code> to match for.
	 * @return <code>Double</code> value of an average ratio.
	 */
	public static Double calculateAverageRatio(String source, String lookup) {
		return (FuzzySearch.weightedRatio(source, lookup) + FuzzySearch.tokenSetRatio(source, lookup)) / 2.0;
	}
}
