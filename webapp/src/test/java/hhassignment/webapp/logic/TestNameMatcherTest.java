package hhassignment.webapp.logic;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TestNameMatcherTest {
	Double threshold = 60.0;

	String source1 = "HDL Cholesterol";
	String source2 = "LDL Cholesterol";
	String source3 = "A1C";
	String source4 = "Neutrophils";
	String source5 = "Lymphocytes";
	String source6 = "Monocytes";
	String source7 = "Eosinophiles";
	String source8 = "Basophils";
	String source9 = "RBC Count";
	String source10 = "Hgb";

	List<String> sources = Arrays.asList(source1, source2, source3, source4, source5, source6, source7, source8,
			source9, source10);

	String lookup1 = "Cholesterol - HDL";
	String lookup2 = "HDL, Total";
	String lookup3 = "CHOLESTEROL-LDL calc";
	String lookup4 = "HM Hemoglobin - A1C";
	String lookup5 = "Triglycerides";
	String lookup6 = "";
	String lookup7 = "Cholesterol GDL";
	String lookup8 = "Neutrofils";
	String lookup9 = "monocytes %";
	String lookup10 = "HGB";
	String lookup11 = "Hemoglobin (HGB)";
	String lookup12 = "Red Blood Cell Count (RBC)";
	String lookup13 = "Lymph. %";

	@Test
	void testRatioTresholdTuning() {
		assertThat(TestNameMatcher.calculateAverageRatio(source1, lookup1)).isGreaterThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source1, lookup2)).isGreaterThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source2, lookup3)).isGreaterThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source3, lookup4)).isGreaterThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source4, lookup8)).isGreaterThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source6, lookup9)).isGreaterThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source10, lookup10)).isGreaterThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source9, lookup12)).isGreaterThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source5, lookup13)).isGreaterThan(threshold);

		assertThat(TestNameMatcher.calculateAverageRatio(source3, lookup1)).isLessThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source3, lookup2)).isLessThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source3, lookup3)).isLessThan(threshold);
		assertThat(TestNameMatcher.calculateAverageRatio(source3, lookup5)).isLessThan(threshold);
	}

	@Test
	void testMatchingResultsStrict() {
		assertEquals(source1, TestNameMatcher.findMatch(sources, lookup1, threshold, true));
		assertEquals(source1, TestNameMatcher.findMatch(sources, lookup2, threshold, true));
		assertEquals(source2, TestNameMatcher.findMatch(sources, lookup3, threshold, true));
		assertEquals(source3, TestNameMatcher.findMatch(sources, lookup4, threshold, true));
		assertEquals(source6, TestNameMatcher.findMatch(sources, lookup9, threshold, true));
		assertEquals(source10, TestNameMatcher.findMatch(sources, lookup11, threshold, true));
		assertEquals(source9, TestNameMatcher.findMatch(sources, lookup12, threshold, true));
		assertEquals(source5, TestNameMatcher.findMatch(sources, lookup13, threshold, true));
		
		assertNull(TestNameMatcher.findMatch(sources, lookup5, threshold, true));
		assertNull(TestNameMatcher.findMatch(sources, lookup6, threshold, true));
		assertNull(TestNameMatcher.findMatch(sources, lookup7, threshold, true));
	}

	@Test
	void testMatchingResultsNotStrict() {
		assertNotNull(TestNameMatcher.findMatch(sources, lookup7, threshold, false));
	}
}