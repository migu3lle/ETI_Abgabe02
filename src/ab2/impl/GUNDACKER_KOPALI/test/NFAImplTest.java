package ab2.impl.GUNDACKER_KOPALI.test;

import ab2.impl.GUNDACKER_KOPALI.NFA;
import ab2.impl.GUNDACKER_KOPALI.fa.exceptions.IllegalCharacterException;
import ab2.impl.GUNDACKER_KOPALI.impl.NFAImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NFAImplTest {
    public static final Set<Integer> acceptingStates = new HashSet<>();
    public static final Set<Character> characters = new HashSet<>();


    @Test
    public void testConstructorAndGetter() {
        acceptingStates.add(2);
        int initialState = 1;
        characters.add('a');
        characters.add('b');

        NFAImpl nfa = new NFAImpl(3, characters, acceptingStates, initialState);

        Assert.assertEquals(characters, nfa.getSymbols());
        Assert.assertEquals(acceptingStates, nfa.getAcceptingStates());
        Assert.assertEquals(1, nfa.getInitialState());
        Assert.assertEquals(true, nfa.isAcceptingState(2));
    }

    @Test
    public void testTransitions() {
        acceptingStates.add(2);
        int initialState = 1;
        characters.add('a');
        characters.add('b');
        characters.add('c');
        characters.add('d');
        NFAImpl nfa = new NFAImpl(3, characters, acceptingStates, initialState);
        nfa.setTransition(0, "a", 1);
        nfa.setTransition(1, "b", 0);
        nfa.setTransition(0, "b", 1);
        nfa.setTransition(0, "c", 1);
        nfa.setTransition(0, "d", 1);
        nfa.setTransition(1, "d", 2);
        nfa.setTransition(1, "a", 2);
        nfa.setTransition(2, "b", 0);
        nfa.setTransition(2, "c", 0);

        Set<String>[][] returnSet = nfa.getTransitions();

        System.out.println("Test: " + returnSet[1][2].toString());
    }

    @Test
    public void testGetNextStates() {
        Set<Integer> expReturnStates = new HashSet<>();
        acceptingStates.add(2);
        int initialState = 1;
        characters.add('a');
        characters.add('b');
        characters.add('c');
        characters.add('d');
        NFAImpl nfa = new NFAImpl(3, characters, acceptingStates, initialState);
        nfa.setTransition(0, "a", 1);
        nfa.setTransition(1, "b", 0);
        nfa.setTransition(0, "b", 1);
        nfa.setTransition(0, "c", 1);
        nfa.setTransition(0, "d", 1);
        nfa.setTransition(0, "b", 2);
        nfa.setTransition(1, "d", 2);
        nfa.setTransition(1, "a", 2);
        nfa.setTransition(2, "b", 0);
        nfa.setTransition(2, "c", 0);

        expReturnStates.add(1);
        expReturnStates.add(2);

        Assert.assertEquals(expReturnStates, nfa.getNextStates(0, "b"));
    }

    @Test(expected = IllegalStateException.class)
    public void testGetNextStatesExceptionStates() {
        acceptingStates.add(2);
        int initialState = 1;
        characters.add('a');

        NFAImpl nfa = new NFAImpl(1, characters, acceptingStates, initialState);
        nfa.getNextStates(1, "a");
    }

    @Test(expected = IllegalCharacterException.class)
    public void testGetNextStatesExceptionChar() {
        acceptingStates.add(2);
        int initialState = 1;
        characters.add('a');

        NFAImpl nfa = new NFAImpl(1, characters, acceptingStates, initialState);
        nfa.getNextStates(0, "b");
    }

    @Test
    public void testAccepts() {
        int initialState = 0;
        acceptingStates.add(3);
        characters.add('a');
        characters.add('b');
        characters.add('c');
        NFAImpl nfa = new NFAImpl(4, characters, acceptingStates, initialState);
        nfa.setTransition(0, "a", 1);
        nfa.setTransition(1, "b", 2);
        nfa.setTransition(2, "b", 2);
        nfa.setTransition(2, "c", 3);
        nfa.setTransition(2, "", 3);

        Assert.assertTrue(nfa.accepts("abb"));
        Assert.assertTrue(nfa.accepts("abc"));
        Assert.assertTrue(nfa.accepts("ab"));
    }

    @Test
    public void acceptsNothingTest() {
        int initialState = 0;
        acceptingStates.add(3);
        characters.add('a');
        NFAImpl nfa = new NFAImpl(4, characters, acceptingStates, initialState);
        nfa.setTransition(0, "a", 1);
        nfa.setTransition(1, "a", 2);
        nfa.setTransition(2, "a", 2);
        nfa.setTransition(3, "a", 3);

        Assert.assertTrue(nfa.acceptsNothing());
    }

    @Test
    public void concatTest() {
        //NFA 1
        acceptingStates.add(1);
        characters.add('a');
        NFAImpl nfa1 = new NFAImpl(2, characters, acceptingStates, 0);
        nfa1.setTransition(0, "a", 1);
        nfa1.setTransition(0, "", 1);

        //NFA 2
        Set<Integer> acceptingStatesNFA2 = new HashSet<>();
        Set<Character> charactersNFA2 = new HashSet<>();
        acceptingStatesNFA2.add(1);
        charactersNFA2.add('b');
        NFAImpl nfa2 = new NFAImpl(2, charactersNFA2, acceptingStatesNFA2, 0);
        nfa2.setTransition(0, "b", 1);
        nfa2.setTransition(0, "", 1);

        NFA nfaConcat = nfa1.concat(nfa2);
        Set<Character> checkCharsSet = new HashSet<>();
        checkCharsSet.add('a');
        checkCharsSet.add('b');
        Set<Integer> checkAcceptingStatesSet = new HashSet<>();
        checkAcceptingStatesSet.add(3);

        //Now check CONCAT NFA for specifications
        Assert.assertEquals(4, nfaConcat.getNumStates());
        Assert.assertEquals(checkCharsSet, nfaConcat.getSymbols());
        Assert.assertEquals(checkAcceptingStatesSet, nfaConcat.getAcceptingStates());
        Assert.assertEquals(0, nfaConcat.getInitialState());

        //Print transitions list to check
        Set<String>[][] concatTransitions = nfaConcat.getTransitions();
        System.out.println("Concat Transitions: ");
        for (int i = 0; i < concatTransitions.length; i++) {
            for (int j = 0; j < concatTransitions[i].length; j++) {
                if (concatTransitions[i][j] != null) {
                    System.out.println(i + " -> " + j + " : " + concatTransitions[i][j]);
                }
            }
        }
        //Check acceptsEpsilon() Method (using accepts() method)
        Assert.assertTrue(nfaConcat.acceptsEpsilon());
    }

    @Test
    public void unionTest(){
        //NFA 1
        acceptingStates.add(1);
        characters.add('a');
        NFAImpl nfa1 = new NFAImpl(2, characters, acceptingStates, 0);
        nfa1.setTransition(0, "a", 1);

        //NFA 2
        Set<Integer> acceptingStatesNFA2 = new HashSet<>();
        Set<Character> charactersNFA2 = new HashSet<>();
        acceptingStatesNFA2.add(1);
        charactersNFA2.add('b');
        NFAImpl nfa2 = new NFAImpl(2, charactersNFA2, acceptingStatesNFA2, 0);
        nfa2.setTransition(0, "b", 1);

        NFA nfaUnion = nfa1.union(nfa2);
        Set<Character> checkCharsSet = new HashSet<>();
        checkCharsSet.add('a');
        checkCharsSet.add('b');
        Set<Integer> checkAcceptingStatesSet = new HashSet<>();
        checkAcceptingStatesSet.add(2);
        checkAcceptingStatesSet.add(4);

        //Now check UNION NFA for specifications
        Assert.assertEquals(5, nfaUnion.getNumStates());
        Assert.assertEquals(checkCharsSet, nfaUnion.getSymbols());
        Assert.assertEquals(checkAcceptingStatesSet, nfaUnion.getAcceptingStates());
        Assert.assertEquals(0, nfaUnion.getInitialState());

        //Print transitions list to check
        Set<String>[][] unionTransitions = nfaUnion.getTransitions();
        System.out.println("Union Transitions: ");
        for (int i = 0; i < unionTransitions.length; i++) {
            for (int j = 0; j < unionTransitions[i].length; j++) {
                if (unionTransitions[i][j] != null) {
                    System.out.println(i + " -> " + j + " : " + unionTransitions[i][j]);
                }
            }
        }

        Assert.assertFalse(nfaUnion.acceptsEpsilon());
        Assert.assertFalse(nfaUnion.accepts("ab"));
        Assert.assertFalse(nfaUnion.accepts(""));
    }

    @Test
    public void complementTest(){
        acceptingStates.add(1);
        characters.add('a');
        NFAImpl nfa1 = new NFAImpl(4, characters, acceptingStates, 0);
        //Get new Complement NFA of NFA1
        NFA newNFA = nfa1.complement();

        Set<Integer> checkAcceptingStates = new HashSet<>();
        checkAcceptingStates.add(0);
        checkAcceptingStates.add(2);
        checkAcceptingStates.add(3);

        Assert.assertEquals(checkAcceptingStates, newNFA.getAcceptingStates());
        Assert.assertEquals(0, newNFA.getInitialState());
        Assert.assertEquals(4, newNFA.getNumStates());
    }



}
