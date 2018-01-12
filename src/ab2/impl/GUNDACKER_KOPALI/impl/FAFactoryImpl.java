package ab2.impl.GUNDACKER_KOPALI.impl;

import ab2.impl.GUNDACKER_KOPALI.*;

import java.util.Set;

public class FAFactoryImpl implements FAFactory {

	public NFA createNFA(int numStates, Set<Character> characters, Set<Integer> acceptingStates, int initialState){
		NFA x = new NFAImpl(numStates, characters, acceptingStates, initialState);
		return x;
	}

	@Override
	public DFA createDFA(int numStates, Set<Character> characters, Set<Integer> acceptingStates, int initialState) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RSA createRSA(int numStates, Set<Character> characters, Set<Integer> acceptingStates, int initialState) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RSA createPatternMatcher(String pattern) {
		// TODO Auto-generated method stub
		return null;
	}
}
