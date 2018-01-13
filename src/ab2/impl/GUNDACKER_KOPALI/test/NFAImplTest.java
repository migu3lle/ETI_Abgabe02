package ab2.impl.GUNDACKER_KOPALI.test;

import ab2.impl.GUNDACKER_KOPALI.fa.exceptions.IllegalCharacterException;
import ab2.impl.GUNDACKER_KOPALI.impl.NFAImpl;
import org.junit.Assert;
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
        Assert.assertEquals(1,nfa.getInitialState());
        Assert.assertEquals(true,nfa.isAcceptingState(2));
    }

    @Test
    public void testTransitions(){
        acceptingStates.add(2);
        int initialState = 1;
        characters.add('a');
        characters.add('b');
        characters.add('c');
        characters.add('d');
        NFAImpl nfa = new NFAImpl(3, characters, acceptingStates, initialState);
        nfa.setTransition(0,"a",1);
        nfa.setTransition(1,"b",0);
        nfa.setTransition(0,"b",1);
        nfa.setTransition(0,"c",1);
        nfa.setTransition(0,"d",1);
        nfa.setTransition(1,"d",2);
        nfa.setTransition(1,"a",2);
        nfa.setTransition(2,"b",0);
        nfa.setTransition(2,"c",0);

        Set<String>[][] returnSet = nfa.getTransitions();

        System.out.println("Test: " + returnSet[1][2].toString());
    }

    @Test
    public void testGetNextStates(){
        Set<Integer> expReturnStates = new HashSet<>();
        acceptingStates.add(2);
        int initialState = 1;
        characters.add('a');
        characters.add('b');
        characters.add('c');
        characters.add('d');
        NFAImpl nfa = new NFAImpl(3, characters, acceptingStates, initialState);
        nfa.setTransition(0,"a",1);
        nfa.setTransition(1,"b",0);
        nfa.setTransition(0,"b",1);
        nfa.setTransition(0,"c",1);
        nfa.setTransition(0,"d",1);
        nfa.setTransition(0,"b",2);
        nfa.setTransition(1,"d",2);
        nfa.setTransition(1,"a",2);
        nfa.setTransition(2,"b",0);
        nfa.setTransition(2,"c",0);

        expReturnStates.add(1);
        expReturnStates.add(2);

        Assert.assertEquals(expReturnStates, nfa.getNextStates(0,"b"));
    }

    @Test(expected = IllegalStateException.class)
    public void testGetNextStatesExceptionStates(){
        acceptingStates.add(2);
        int initialState = 1;
        characters.add('a');

        NFAImpl nfa = new NFAImpl(1, characters, acceptingStates, initialState);
        nfa.getNextStates(1,"a");
    }

    @Test(expected = IllegalCharacterException.class)
    public void testGetNextStatesExceptionChar(){
        acceptingStates.add(2);
        int initialState = 1;
        characters.add('a');

        NFAImpl nfa = new NFAImpl(1, characters, acceptingStates, initialState);
        nfa.getNextStates(0,"b");
    }

    @Test
    public void testAccepts(){
        int initialState = 0;
        acceptingStates.add(3);
        acceptingStates.add(2);
        characters.add('a');
        characters.add('b');
        characters.add('c');
        NFAImpl nfa = new NFAImpl(4, characters, acceptingStates, initialState);
        nfa.setTransition(0,"a",1);
        nfa.setTransition(1,"b",2);
        nfa.setTransition(2,"b",2);
        nfa.setTransition(2,"c",3);

        Assert.assertTrue(nfa.accepts("abb"));
        Assert.assertTrue(nfa.accepts("abc"));
    }

    @Test
    public void acceptsNothingTest(){
        int initialState = 0;
        acceptingStates.add(3);
        characters.add('a');
        NFAImpl nfa = new NFAImpl(4, characters, acceptingStates, initialState);
        nfa.setTransition(0,"a",1);
        nfa.setTransition(1,"a",2);
        nfa.setTransition(2,"a",2);
        nfa.setTransition(3,"a",3);

        Assert.assertTrue(nfa.acceptsNothing());
    }


}
