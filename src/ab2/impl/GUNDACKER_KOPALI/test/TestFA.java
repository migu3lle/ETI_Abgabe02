package ab2.impl.GUNDACKER_KOPALI.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ab2.impl.GUNDACKER_KOPALI.FAFactory;
import ab2.impl.GUNDACKER_KOPALI.NFA;
import ab2.impl.GUNDACKER_KOPALI.RSA;
import ab2.impl.GUNDACKER_KOPALI.impl.FAFactoryImpl;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFA {
	private NFA n1; // leere Menge
	private NFA n2; // epsilon
	private NFA n3; // a*
	private NFA n4; // {a,b}*
	private NFA n5; // {a,b}* | c*
	private NFA n6; // {a,b,c}*
	private NFA n7; // Bsp 5.1
	private NFA n8; // Bsp 5.2
	private NFA n9; // irgendwas

	private List<NFA> nfas = new ArrayList<NFA>();

	FAFactory factory = new FAFactoryImpl();

	public static final Set<Character> chars = new HashSet<>();

	static {
		chars.add('a');
		chars.add('b');
		chars.add('c');
	}

	@Before
	public void InitializeNFA1() {
		Set<Integer> accept = new TreeSet<Integer>();

		n1 = factory.createNFA(1, chars, accept, 0);
		nfas.add(n1);
	}

	@Before
	public void InitializeNFA2() {
		Set<Integer> accept = new TreeSet<Integer>();
		accept.add(0);

		n2 = factory.createNFA(5, chars, accept, 0);
		nfas.add(n2);

		n2.setTransition(1, "a", 2);
		n2.setTransition(2, "a", 3);
		n2.setTransition(3, "a", 4);
		n2.setTransition(4, "a", 0);
	}

	@Before
	public void InitializeNFA3() {
		Set<Integer> accept = new TreeSet<Integer>();
		accept.add(0);

		n3 = factory.createNFA(1, chars, accept, 0);
		nfas.add(n3);

		n3.setTransition(0, "a", 0);
	}

	@Before
	public void InitializeNFA4() {
		Set<Integer> accept = new TreeSet<Integer>();
		accept.add(0);

		n4 = factory.createNFA(5, chars, accept, 0);
		nfas.add(n4);

		n4.setTransition(0, "a", 0);
		n4.setTransition(0, "b", 0);
	}

	@Before
	public void InitializeNFA5() {
		Set<Integer> accept = new TreeSet<Integer>();
		accept.add(0);
		accept.add(1);

		n5 = factory.createNFA(2, chars, accept, 0);
		nfas.add(n5);

		n5.setTransition(0, "a", 0);
		n5.setTransition(0, "b", 0);
		n5.setTransition(1, "c", 1);
		n5.setTransition(0, "", 1);
	}

	@Before
	public void InitializeNFA6() {
		Set<Integer> accept = new TreeSet<Integer>();
		accept.add(0);
		accept.add(1);

		n6 = factory.createNFA(2, chars, accept, 0);
		nfas.add(n6);

		n6.setTransition(0, "a", 0);
		n6.setTransition(0, "b", 0);
		n6.setTransition(1, "c", 1);
		n6.setTransition(0, "", 1);
		n6.setTransition(1, "", 0);
	}

	@Before
	public void InitializeNFA7() {
		Set<Integer> accept = new TreeSet<Integer>();
		accept.add(4);

		n7 = factory.createNFA(5, chars, accept, 0);
		nfas.add(n7);

		n7.setTransition(0, "b", 1);
		n7.setTransition(0, "a", 2);
		n7.setTransition(0, "a", 3);
		n7.setTransition(1, "b", 2);
		n7.setTransition(1, "a", 4);
		n7.setTransition(2, "a", 2);
		n7.setTransition(2, "b", 4);
		n7.setTransition(3, "b", 2);
	}

	@Before
	public void InitializeNFA8() {
		Set<Integer> accept = new TreeSet<Integer>();
		accept.add(3);
		accept.add(4);

		n8 = factory.createNFA(5, chars, accept, 0);
		nfas.add(n8);

		n8.setTransition(0, "a", 1);
		n8.setTransition(0, "b", 2);
		n8.setTransition(1, "b", 4);
		n8.setTransition(2, "b", 2);
		n8.setTransition(2, "a", 1);
		n8.setTransition(2, "a", 3);
		n8.setTransition(4, "b", 2);
		n8.setTransition(4, "a", 3);

		n8.setTransition(1, "", 0);
		n8.setTransition(4, "", 0);
		n8.setTransition(3, "", 1);
		n8.setTransition(4, "", 2);
	}

	@Before
	public void InitializeNFA9() {
		Set<Integer> accept = new TreeSet<Integer>();
		accept.add(7);

		n9 = factory.createNFA(8, chars, accept, 0);
		nfas.add(n9);

		n9.setTransition(5, "a", 6);
		n9.setTransition(5, "b", 1);
		n9.setTransition(6, "b", 7);
		n9.setTransition(6, "a", 3);
		n9.setTransition(7, "b", 7);
		n9.setTransition(7, "a", 7);
		n9.setTransition(3, "b", 4);
		n9.setTransition(3, "a", 0);
		n9.setTransition(0, "b", 1);
		n9.setTransition(0, "a", 0);
		n9.setTransition(2, "a", 3);
		n9.setTransition(2, "b", 1);
		n9.setTransition(1, "a", 2);
		n9.setTransition(1, "b", 1);
		n9.setTransition(4, "b", 5);
		n9.setTransition(4, "a", 2);
		n9.setTransition(1, "c", 4);
		n9.setTransition(2, "c", 6);
		n9.setTransition(5, "c", 3);
		n9.setTransition(1, "", 6);
		n9.setTransition(2, "", 4);
		n9.setTransition(7, "", 3);
	}

	private void testToRSA(NFA n) {
		RSA r = n.toRSA();
		assertEquals(true, isValidRSA(r));
	}

	@Test
	public void test_ToRSA() {
		testToRSA(n1);
		testToRSA(n2);
		testToRSA(n3);
		testToRSA(n4);
		testToRSA(n5);
		testToRSA(n6);
		testToRSA(n7);
		testToRSA(n8);
		testToRSA(n9);
	}

	@Test
	public void test_NFA_Language() {
		testLanguageNFA1(n1);

		testLanguageNFA2(n2);

		testLanguageNFA3(n3);

		testLanguageNFA4(n4);

		testLanguageNFA5(n5);

		testLanguageNFA6(n6);

		testLanguageNFA7(n7);

		testLanguageNFA8(n8);

		testLanguageNFA9(n9);

		// testLanguageNFA10(n10);
	}

	@Test
	public void test_ToRSA_Language() {
		testLanguageNFA1(n1.toRSA());

		testLanguageNFA2(n2.toRSA());

		testLanguageNFA3(n3.toRSA());

		testLanguageNFA4(n4.toRSA());

		testLanguageNFA5(n5.toRSA());

		testLanguageNFA6(n6.toRSA());

		testLanguageNFA7(n7.toRSA());

		testLanguageNFA8(n8.toRSA());

		testLanguageNFA9(n9.toRSA());

		// testLanguageNFA10(n10.toRSA());
	}

	@Test
	public void test_ToRSA_Properties() {
		testPropertiesNFA1(n1.toRSA());

		testPropertiesNFA2(n2.toRSA());

		testPropertiesNFA3(n3.toRSA());

		testPropertiesNFA4(n4.toRSA());

		testPropertiesNFA5(n5.toRSA());

		testPropertiesNFA6(n6.toRSA());

		testPropertiesNFA7(n7.toRSA());

		testPropertiesNFA8(n8.toRSA());

		testPropertiesNFA9(n9.toRSA());
	}

	private void testLanguageNFA1(NFA n) {
		assertEquals(false, n.accepts(""));
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("b"));
		assertEquals(false, n.accepts("c"));
	}

	private void testLanguageNFA2(NFA n) {
		assertEquals(true, n.accepts(""));
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("b"));
		assertEquals(false, n.accepts("c"));
	}

	private void testLanguageNFA3(NFA n) {
		assertEquals(true, n.accepts(""));
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aaa"));
		assertEquals(false, n.accepts("b"));
		assertEquals(false, n.accepts("c"));
		assertEquals(false, n.accepts("aaac"));
	}

	private void testLanguageNFA4(NFA n) {
		assertEquals(true, n.accepts(""));
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aaa"));
		assertEquals(true, n.accepts("b"));
		assertEquals(true, n.accepts("abba"));
		assertEquals(false, n.accepts("c"));
		assertEquals(false, n.accepts("aaac"));
	}

	private void testLanguageNFA5(NFA n) {
		assertEquals(true, n.accepts(""));
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aaa"));
		assertEquals(true, n.accepts("b"));
		assertEquals(true, n.accepts("abba"));
		assertEquals(true, n.accepts("c"));
		assertEquals(true, n.accepts("aaac"));
	}

	private void testLanguageNFA6(NFA n) {
		assertEquals(true, n.accepts(""));
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aaa"));
		assertEquals(true, n.accepts("b"));
		assertEquals(true, n.accepts("abba"));
		assertEquals(true, n.accepts("c"));
		assertEquals(true, n.accepts("aaac"));
	}

	private void testLanguageNFA7(NFA n) {
		// folgt
	}

	private void testLanguageNFA8(NFA n) {
		// folgt
	}

	private void testLanguageNFA9(NFA n) {
		// folgt
	}

	@Test
	public void test_NFA_Properties() {
		testPropertiesNFA1(n1);
		testPropertiesNFA2(n2);
		testPropertiesNFA3(n3);
		testPropertiesNFA4(n4);
		testPropertiesNFA5(n5);
		testPropertiesNFA6(n6);
		testPropertiesNFA7(n7);
		testPropertiesNFA8(n8);
		testPropertiesNFA9(n9);
	}

	private void testPropertiesNFA1(NFA n) {
		assertEquals(true, n.acceptsNothing());
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
	}

	private void testPropertiesNFA2(NFA n) {
		assertEquals(false, n.acceptsNothing());
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(true, n.acceptsEpsilonOnly());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
	}

	private void testPropertiesNFA3(NFA n) {
		assertEquals(false, n.acceptsNothing());
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
	}

	private void testPropertiesNFA4(NFA n) {
		assertEquals(false, n.acceptsNothing());
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
	}

	private void testPropertiesNFA5(NFA n) {
		assertEquals(false, n.acceptsNothing());
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
	}

	private void testPropertiesNFA6(NFA n) {
		assertEquals(false, n.acceptsNothing());
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
	}

	private void testPropertiesNFA7(NFA n) {
		assertEquals(false, n.acceptsNothing());
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
	}

	private void testPropertiesNFA8(NFA n) {
		assertEquals(false, n.acceptsNothing());
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
	}

	private void testPropertiesNFA9(NFA n) {
		// folgt
	}

	/*@Test
	public void test_NFA_Ext() {
		for (int i = 0; i < nfas.size(); i++)
			assertEquals(true, nfas.get(i).equals(nfas.get(i)));

		for (int i = 0; i < nfas.size(); i++)
			for (int j = i + 1; j < nfas.size(); j++)
				assertEquals(false, nfas.get(i).equals(nfas.get(j)));

		assertEquals(true, n1.subSetOf(n2));
		assertEquals(true, n2.subSetOf(n3));
		assertEquals(true, n3.subSetOf(n4));
		assertEquals(true, n4.subSetOf(n5));
		assertEquals(true, n5.subSetOf(n6));
	}*/

	@Test
	public void test_NFA_Operations() {
		////////////////////// Vereinigung ///////////////////

		NFA n = n1.union(n2);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(true, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n2.union(n3);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		//////////////////////////

		n = n3.union(n4);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n4.union(n5);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		///////////////////////

		n = n5.union(n6);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n6.union(n7);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n7.union(n8);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n8.union(n9);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////// Durchschnitt ///////////////////

		n = n1.intersection(n2);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n2.intersection(n3);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(true, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		//////////////////////////

		n = n3.intersection(n4);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n4.intersection(n5);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		///////////////////////

		n = n5.intersection(n6);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n6.intersection(n7);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n7.intersection(n8);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n8.intersection(n9);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////// Ohne ///////////////////

		n = n1.minus(n2);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n2.minus(n3);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		//////////////////////////

		n = n3.minus(n4);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n4.minus(n5);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		///////////////////////

		n = n5.minus(n6);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n6.minus(n7);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n7.minus(n8);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n8.minus(n9);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////// Concat ///////////////////

		n = n1.concat(n2);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n2.concat(n3);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		//////////////////////////

		n = n3.concat(n4);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n4.concat(n5);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		///////////////////////

		n = n5.concat(n6);
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n6.concat(n7);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n7.concat(n8);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n8.concat(n9);
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////// Stern ///////////////////

		n = n1.kleeneStar();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(true, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n2.kleeneStar();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(true, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		//////////////////////////

		n = n3.kleeneStar();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n4.kleeneStar();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		///////////////////////

		n = n5.kleeneStar();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n6.kleeneStar();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n7.kleeneStar();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n8.kleeneStar();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		n = n9.kleeneStar();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////// Plus ///////////////////

		n = n1.plus();
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n2.plus();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(true, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		//////////////////////////

		n = n3.plus();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n4.plus();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		///////////////////////

		n = n5.plus();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n6.plus();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n7.plus();
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n8.plus();
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		n = n9.plus();
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////// Complement ///////////////////

		n = n1.complement();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n2.complement();
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		//////////////////////////

		n = n3.complement();
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n4.complement();
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		///////////////////////

		n = n5.complement();
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n6.complement();
		assertEquals(false, n.acceptsEpsilon());
		assertEquals(true, n.isFinite());
		assertEquals(false, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(false, n.equalsPlusAndStar());
		assertEquals(false, n.accepts("a"));
		assertEquals(false, n.accepts("aa"));
		assertEquals(false, n.accepts("aabc"));

		////////////////////////

		n = n7.complement();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		////////////////////////

		n = n8.complement();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

		n = n9.complement();
		assertEquals(true, n.acceptsEpsilon());
		assertEquals(false, n.isFinite());
		assertEquals(true, n.isInfinite());
		assertEquals(false, n.acceptsEpsilonOnly());
		assertEquals(true, n.equalsPlusAndStar());
		assertEquals(true, n.accepts("a"));
		assertEquals(true, n.accepts("aa"));
		assertEquals(true, n.accepts("aabc"));

	}

	@Test
	public void test_Minimization() {
		RSA r1 = n1.toRSA().minimize();
		RSA r2 = n2.toRSA().minimize();
		RSA r3 = n3.toRSA().minimize();
		RSA r4 = n4.toRSA().minimize();
		RSA r5 = n5.toRSA().minimize();
		RSA r6 = n6.toRSA().minimize();
		RSA r7 = n7.toRSA().minimize();
		RSA r8 = n8.toRSA().minimize();
		RSA r9 = n9.toRSA().minimize();

		// Teste die Größen der resultierenden Automaten

		assertEquals(1, r1.getNumStates());
		assertEquals(2, r2.getNumStates());
		assertEquals(2, r3.getNumStates());
		assertEquals(2, r4.getNumStates());
		assertEquals(3, r5.getNumStates());
		assertEquals(1, r6.getNumStates());
		assertEquals(7, r7.getNumStates());
		assertEquals(6, r8.getNumStates());
		assertEquals(24, r9.getNumStates());
		// assertEquals(7, r10.getNumStates());

		// Teste die Sprache der Automaten
		testLanguageNFA1(r1);
		testLanguageNFA2(r2);
		testLanguageNFA3(r3);
		testLanguageNFA4(r4);
		testLanguageNFA5(r5);
		testLanguageNFA6(r6);
		testLanguageNFA7(r7);
		testLanguageNFA8(r8);
		testLanguageNFA9(r9);
		// testLanguageNFA10(r10);

		// Teste die Eigenschaften der Automaten
		testPropertiesNFA1(r1);
		testPropertiesNFA2(r2);
		testPropertiesNFA3(r3);
		testPropertiesNFA4(r4);
		testPropertiesNFA5(r5);
		testPropertiesNFA6(r6);
		testPropertiesNFA7(r7);
		testPropertiesNFA8(r8);
		testPropertiesNFA9(r9);
		// testPropertiesNFA10(r10);
	}

	@Test
	public void test_Minimization_Operations() {

		assertEquals(2, n1.union(n2).toRSA().minimize().getNumStates());
		assertEquals(2, n2.union(n3).toRSA().minimize().getNumStates());
		assertEquals(2, n3.union(n4).toRSA().minimize().getNumStates());
		assertEquals(3, n4.union(n5).toRSA().minimize().getNumStates());
		assertEquals(1, n5.union(n6).toRSA().minimize().getNumStates());
		assertEquals(1, n6.union(n7).toRSA().minimize().getNumStates());
		assertEquals(10, n7.union(n8).toRSA().minimize().getNumStates());
		assertEquals(32, n8.union(n9).toRSA().minimize().getNumStates());

		assertEquals(1, n1.concat(n2).toRSA().minimize().getNumStates());
		assertEquals(2, n2.concat(n3).toRSA().minimize().getNumStates());
		assertEquals(2, n3.concat(n4).toRSA().minimize().getNumStates());
		assertEquals(3, n4.concat(n5).toRSA().minimize().getNumStates());
		assertEquals(1, n5.concat(n6).toRSA().minimize().getNumStates());
		assertEquals(6, n6.concat(n7).toRSA().minimize().getNumStates());
		assertEquals(9, n7.concat(n8).toRSA().minimize().getNumStates());
		assertEquals(27, n8.concat(n9).toRSA().minimize().getNumStates());

		assertEquals(1, n1.intersection(n2).toRSA().minimize().getNumStates());
		assertEquals(2, n2.intersection(n3).toRSA().minimize().getNumStates());
		assertEquals(2, n3.intersection(n4).toRSA().minimize().getNumStates());
		assertEquals(2, n4.intersection(n5).toRSA().minimize().getNumStates());
		assertEquals(3, n5.intersection(n6).toRSA().minimize().getNumStates());
		assertEquals(7, n6.intersection(n7).toRSA().minimize().getNumStates());
		assertEquals(8, n7.intersection(n8).toRSA().minimize().getNumStates());
		assertEquals(10, n8.intersection(n9).toRSA().minimize().getNumStates());

		assertEquals(1, n1.minus(n2).toRSA().minimize().getNumStates());
		assertEquals(1, n2.minus(n3).toRSA().minimize().getNumStates());
		assertEquals(1, n3.minus(n4).toRSA().minimize().getNumStates());
		assertEquals(1, n4.minus(n5).toRSA().minimize().getNumStates());
		assertEquals(1, n5.minus(n6).toRSA().minimize().getNumStates());
		assertEquals(7, n6.minus(n7).toRSA().minimize().getNumStates());
		assertEquals(5, n7.minus(n8).toRSA().minimize().getNumStates());
		assertEquals(9, n8.minus(n9).toRSA().minimize().getNumStates());
		assertEquals(24, n9.minus(n1).toRSA().minimize().getNumStates());

		assertEquals(1, n1.plus().toRSA().minimize().getNumStates());
		assertEquals(2, n2.plus().toRSA().minimize().getNumStates());
		assertEquals(2, n3.plus().toRSA().minimize().getNumStates());
		assertEquals(2, n4.plus().toRSA().minimize().getNumStates());
		assertEquals(1, n5.plus().toRSA().minimize().getNumStates());
		assertEquals(1, n6.plus().toRSA().minimize().getNumStates());
		assertEquals(11, n7.plus().toRSA().minimize().getNumStates());
		assertEquals(6, n8.plus().toRSA().minimize().getNumStates());
		assertEquals(21, n9.plus().toRSA().minimize().getNumStates());

		assertEquals(2, n1.kleeneStar().toRSA().minimize().getNumStates());
		assertEquals(2, n2.kleeneStar().toRSA().minimize().getNumStates());
		assertEquals(2, n3.kleeneStar().toRSA().minimize().getNumStates());
		assertEquals(2, n4.kleeneStar().toRSA().minimize().getNumStates());
		assertEquals(1, n5.kleeneStar().toRSA().minimize().getNumStates());
		assertEquals(1, n6.kleeneStar().toRSA().minimize().getNumStates());
		assertEquals(10, n7.kleeneStar().toRSA().minimize().getNumStates());
		assertEquals(6, n8.kleeneStar().toRSA().minimize().getNumStates());
		assertEquals(22, n9.kleeneStar().toRSA().minimize().getNumStates());

		assertEquals(1, n1.complement().toRSA().minimize().getNumStates());
		assertEquals(2, n2.complement().toRSA().minimize().getNumStates());
		assertEquals(2, n3.complement().toRSA().minimize().getNumStates());
		assertEquals(2, n4.complement().toRSA().minimize().getNumStates());
		assertEquals(3, n5.complement().toRSA().minimize().getNumStates());
		assertEquals(1, n6.complement().toRSA().minimize().getNumStates());
		assertEquals(7, n7.complement().toRSA().minimize().getNumStates());
		assertEquals(6, n8.complement().toRSA().minimize().getNumStates());
		assertEquals(24, n9.complement().toRSA().minimize().getNumStates());
	}

	@Test
	public void test_PatternMatching() {
		// folgt
		assertFalse(true);
	}


	private static boolean isValidRSA(RSA r) {
		for (int s = 0; s < r.getNumStates(); s++) {
			for (Character c : r.getSymbols()) {
				// Für jedes Symbol muss es einen Folgezustand geben.
				if (r.getNextState(s, c) == null)
					return false;
			}

			Set<Integer> nextS = r.getNextStates(s, "");
			// RSA dürfen keine epsilon-Übergänge haben
			if (!(nextS.contains(s) && nextS.size() == 1))
				return false;
		}

		return true;
	}
}
