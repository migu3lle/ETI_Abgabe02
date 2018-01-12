package ab2.impl.GUNDACKER_KOPALI.test;

import ab2.impl.GUNDACKER_KOPALI.impl.NFAImpl;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class NFAImplTest {
    public static final Set<Integer> acceptingStates = new HashSet<>();
    public static final Set<Character> characters = new HashSet<>();
    @Test
    public void testClass() {
        acceptingStates.add(3);
        int initialState = 1;
        characters.add('a');
        characters.add('b');
        characters.add('c');
        //Test comment
        characters.add('D');

        NFAImpl nfa = new NFAImpl(3, characters, acceptingStates, initialState);
        System.out.println(nfa.getSymbols());
    }
}
