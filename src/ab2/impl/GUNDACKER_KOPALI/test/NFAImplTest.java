package ab2.impl.GUNDACKER_KOPALI.test;

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
        /*
        Object[] objArray;
        for (int i = 0; i < returnSet.length; i++) {
            for (int j = 0; j < returnSet[i].length; j++) {
                objArray = returnSet[i][j].toArray();
                for (int k = 0; k < objArray.length; k++) {
                    System.out.println(objArray[k]);
                }
            }
        }
        */
        System.out.println("Test: " + returnSet[1][2].toString());
        //System.out.println(returnSet[1][0].toString());
    }
}
