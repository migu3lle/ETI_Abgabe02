package ab2.impl.GUNDACKER_KOPALI.impl;

import ab2.impl.GUNDACKER_KOPALI.NFA;
import ab2.impl.GUNDACKER_KOPALI.RSA;
import ab2.impl.GUNDACKER_KOPALI.fa.exceptions.IllegalCharacterException;

import java.util.*;

public class NFAImpl implements NFA {

    private Set<Character> characters;
    private Set<Integer> acceptingStates;
    private int initialState;
    private Set<Integer> statesSet;
    private List<Transition> transitionList;

    //Constructor
    public NFAImpl(int numStates, Set<Character> characters, Set<Integer> acceptingStates, int initialState){
        this.characters = characters;           //Set characters of NFAImpl
        statesSet = new HashSet<Integer>();
        for(int i = 0; i < numStates; i++) {    //Create statesSet of NFAImpl
            this.statesSet.add(i);
        }
        this.acceptingStates = acceptingStates; //Set accepting statesSet of NFAImpl
        this.initialState = initialState;       //Set initial state of NFAImpl
        this.transitionList = new ArrayList<>();
    }

    public Set<Character> getSymbols(){
        return characters;
    }

    @Override
    public Set<Integer> getAcceptingStates() {
        return acceptingStates;
    }

    @Override
    public int getInitialState() {
        return initialState;
    }

    @Override
    public boolean isAcceptingState(int s) throws IllegalStateException {
        if(!(statesSet.contains(s))){
            throw new IllegalStateException("Zustand existiert nicht. (isAcceptingState)");
        }
        else if(acceptingStates.contains(s)){
            return true;
        }
        else
            return false;
    }

    @Override
    public Set<String>[][] getTransitions() {
        Set<String>[][] transSet = new Set[statesSet.size()][statesSet.size()];
        Set<String> tempSet = new HashSet<>();

        String[] stringArray;
        boolean[][] stateChecked = new boolean[statesSet.size()][statesSet.size()];

        for (int i = 0; i < transitionList.size(); i++) {
            if(stateChecked[transitionList.get(i).getFromState()][transitionList.get(i).getToState()] == false) {
                tempSet.clear();
                tempSet.add(transitionList.get(i).getS());
                for (int j = i + 1; j < transitionList.size(); j++) {
                    if (transitionList.get(i).getFromState() == transitionList.get(j).getFromState()) {
                        if (transitionList.get(i).getToState() == transitionList.get(j).getToState()) {
                            tempSet.add(transitionList.get(j).getS());
                        }
                    }
                }
                stringArray = tempSet.toArray(new String[tempSet.size()]);
                transSet[transitionList.get(i).getFromState()][transitionList.get(i).getToState()] = new HashSet<String>(Arrays.asList(stringArray));
            }
            stateChecked[transitionList.get(i).getFromState()][transitionList.get(i).getToState()] = true;
        }
        return transSet;
    }

    @Override
    public void setTransition(int fromState, String s, int toState) throws IllegalStateException, IllegalCharacterException {
        if(!(statesSet.contains(fromState)) || !(statesSet.contains(toState))){
            throw new IllegalStateException("Zustand existiert nicht. (setTransition)");
        }
        else{
            for(char c : s.toCharArray()){
                if(!characters.contains(c)){
                    throw new IllegalCharacterException();
                }
            }
            transitionList.add(new Transition(fromState, s, toState));
        }
    }

    @Override
    public void clearTransitions(int fromState, String s) throws IllegalStateException {
        Iterator<Transition> it = transitionList.iterator();
        while(it.hasNext()){
            Transition trans = it.next();
            if(trans.getFromState() == fromState && trans.getS() == s){
                it.remove();
            }
        }
    }

    @Override
    public Set<Integer> getNextStates(int state, String s) throws IllegalCharacterException, IllegalStateException {
        char inputChar = s.charAt(0);
        if(!statesSet.contains(state)){
            throw new IllegalStateException("Zustand existiert nicht. (getNextStates)");
        }
        else if(!characters.contains(inputChar)){
            throw new IllegalCharacterException();
        }
        else {
            Set<Integer> retSet = new HashSet<>();
            for (int i = 0; i < transitionList.size(); i++) {
                if (transitionList.get(i).getFromState() == state && transitionList.get(i).getS() == s) {
                    retSet.add(transitionList.get(i).getToState());
                }
            }
            return retSet;
        }
    }

    @Override
    public int getNumStates() {
        return statesSet.size();
    }

    @Override
    public NFA union(NFA a) {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public NFA intersection(NFA a) {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public NFA complement() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public NFA concat(NFA a) {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public NFA kleeneStar() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public NFA plus() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public NFA minus(NFA a) {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public RSA toRSA() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Boolean accepts(String w) throws IllegalCharacterException {
        boolean accepts = false;
        int state = initialState;
        boolean transactionFound;

        for (int i = 0; i < w.length(); i++) {
            transactionFound = false;
            for (int j = 0; j < transitionList.size() && transactionFound == false; j++) {
                if(transitionList.get(j).getFromState() == state && transitionList.get(j).getS().equals(Character.toString(w.charAt(i)))){
                    state = transitionList.get(i).getToState();
                    System.out.println(state);
                    transactionFound = true;
                }
            }
            if(transactionFound == false){
                return accepts;
            }
        }
        if(acceptingStates.contains(state)){
            accepts = true;
        }
        return accepts;
    }

    @Override
    public Boolean acceptsNothing() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Boolean acceptsEpsilonOnly() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Boolean acceptsEpsilon() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Boolean isInfinite() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Boolean isFinite() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Boolean subSetOf(NFA a) {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Boolean equalsPlusAndStar() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

}
