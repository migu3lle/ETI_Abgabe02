package ab2.impl.GUNDACKER_KOPALI.test;

import ab2.impl.GUNDACKER_KOPALI.impl.NFAImpl;
import org.junit.Assert;
import org.junit.Test;

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
        NFAImpl nfa = new NFAImpl(3, characters, acceptingStates, initialState);
        nfa.setTransition(1,"a",2);
        nfa.setTransition(2,"b",1);
        System.out.println(nfa.getTransitions());
    }
}
