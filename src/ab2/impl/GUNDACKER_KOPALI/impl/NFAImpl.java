package ab2.impl.GUNDACKER_KOPALI.impl;

import ab2.impl.GUNDACKER_KOPALI.FAFactory;
import ab2.impl.GUNDACKER_KOPALI.NFA;
import ab2.impl.GUNDACKER_KOPALI.RSA;
import ab2.impl.GUNDACKER_KOPALI.fa.exceptions.IllegalCharacterException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class NFAImpl implements NFA {

    private Set<Character> characters;
    private Set<Integer> acceptingStates;
    private int initialState;
    private Set<Integer> states;
    private List<Transition> transitionList;

    //Constructor
    public NFAImpl(int numStates, Set<Character> characters, Set<Integer> acceptingStates, int initialState){
        this.characters = characters;           //Set characters of NFAImpl
        states = new HashSet<Integer>();
        for(int i = 0; i < numStates; i++) {    //Create states of NFAImpl
            this.states.add(i);
        }
        this.acceptingStates = acceptingStates; //Set accepting states of NFAImpl
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
        if(!(states.contains(s))){
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
        Set<String>[][] transSet = new Set[][];
        Set<String> testSet = new HashSet<>();
        testSet.add(Integer.toString(transitionList.get(0).getFromState()));
        testSet.add(Integer.toString(transitionList.get(1).getFromState()));


        transSet[0][0] = testSet;

        System.out.println(Integer.toString(transitionList.get(0).getFromState()));



        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void setTransition(int fromState, String s, int toState) throws IllegalStateException, IllegalCharacterException {
        if(!(states.contains(fromState)) || !(states.contains(toState))){
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
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public int getNumStates() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
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
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
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
