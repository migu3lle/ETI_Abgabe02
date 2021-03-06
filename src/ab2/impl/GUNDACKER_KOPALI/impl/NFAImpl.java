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
    public NFAImpl(int numStates, Set<Character> characters, Set<Integer> acceptingStates, int initialState) {
        this.characters = characters;           //Set characters of NFAImpl
        statesSet = new HashSet<Integer>();
        for (int i = 0; i < numStates; i++) {    //Create statesSet of NFAImpl
            this.statesSet.add(i);
        }
        this.acceptingStates = acceptingStates; //Set accepting statesSet of NFAImpl
        this.initialState = initialState;       //Set initial state of NFAImpl
        this.transitionList = new ArrayList<>();
    }

    public Set<Character> getSymbols() {
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
        if (!(statesSet.contains(s))) {
            throw new IllegalStateException("Zustand existiert nicht. (isAcceptingState)");
        } else if (acceptingStates.contains(s)) {
            return true;
        } else
            return false;
    }

    @Override
    public Set<String>[][] getTransitions() {
        Set<String>[][] transSet = new Set[statesSet.size()][statesSet.size()];
        Set<String> tempSet = new HashSet<>();

        String[] stringArray;
        boolean[][] stateChecked = new boolean[statesSet.size()][statesSet.size()];

        for (int i = 0; i < transitionList.size(); i++) {
            if (stateChecked[transitionList.get(i).getFromState()][transitionList.get(i).getToState()] == false) {
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
        if (!(statesSet.contains(fromState)) || !(statesSet.contains(toState))) {
            throw new IllegalStateException("Zustand existiert nicht. (setTransition)");
        } else {
            for (char c : s.toCharArray()) {
                if (!characters.contains(c)) {
                    throw new IllegalCharacterException();
                }
            }
            transitionList.add(new Transition(fromState, s, toState));
        }
    }

    @Override
    public void clearTransitions(int fromState, String s) throws IllegalStateException {
        Iterator<Transition> it = transitionList.iterator();
        while (it.hasNext()) {
            Transition trans = it.next();
            if (trans.getFromState() == fromState && trans.getS() == s) {
                it.remove();
            }
        }
    }

    @Override
    public Set<Integer> getNextStates(int state, String s) throws IllegalCharacterException, IllegalStateException {
        char inputChar = s.charAt(0);
        if (!statesSet.contains(state)) {
            throw new IllegalStateException("Zustand existiert nicht. (getNextStates)");
        } else if (!characters.contains(inputChar)) {
            throw new IllegalCharacterException();
        } else {
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
        NFAImpl unionNFA;

        Set<Character> unionChars = new HashSet<>();
        Set<Integer> unionAcceptingStates = new HashSet<>();
        int unionInitialState = 0;
        Set<Integer> unionStatesSet = new HashSet<>();
        List<Transition> unionTransitionList = new ArrayList<>();

        //Union characters
        unionChars.addAll(a.getSymbols());
        unionChars.addAll(this.getSymbols());
        //Add THIS's accepting states to Union's accepting states
        for (int i : this.getAcceptingStates()) {
            unionAcceptingStates.add(i + 1);
        }
        //Add a's accepting states to Union's accepting states
        for (int i : a.getAcceptingStates()) {
            unionAcceptingStates.add(i + this.getNumStates() + 1);
        }

        unionNFA = new NFAImpl(a.getNumStates() + this.getNumStates() + 1, unionChars, unionAcceptingStates, unionInitialState);

        //Adding transitions of THIS NFA to Union NFA, considering additional initialState (+1)
        for (int i = 0; i < this.transitionList.size(); i++) {
            unionNFA.setTransition(this.transitionList.get(i).getFromState() + 1, this.transitionList.get(i).getS(),this.transitionList.get(i).getToState() + 1);
        }

        //Adding transitions of A.NFA to Union NFA, considering additional initialState (+1)
        Set<String>[][] transitionsA = a.getTransitions();
        for (int i = 0; i < transitionsA.length; i++) {
            for (int j = 0; j < transitionsA[i].length; j++) {
                if (transitionsA[i][j] != null) {
                    //Adding transitions
                    for (String s : transitionsA[i][j]) {
                        unionNFA.setTransition(i + this.getNumStates() + 1, s, j + this.getNumStates() + 1);
                    }
                }
            }
        }

        //Epsilon Übergang für Union, von neuem Startzustand zu THIS.NFA-Startzustand und A.NFA-Startzustand
        unionNFA.setTransition(0, "", this.getInitialState() + 1);
        unionNFA.setTransition(0, "", a.getInitialState() + this.getNumStates() + 1);

        return unionNFA;
    }

    @Override
    public NFA intersection(NFA a) {

        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public NFA complement() {
        NFAImpl complementNFA;
        Set<Integer> complementAcceptingStates = new HashSet<>();

        //Tausche Endzustände mit Nicht-Endzuständen
        for (Integer i : this.statesSet){
            if(this.acceptingStates.contains(i)){
                this.acceptingStates.remove(i);
            }
            else
                this.acceptingStates.add(i);
        }
        complementNFA = new NFAImpl(this.getNumStates(),this.getSymbols(), this.acceptingStates, this.initialState);
        return complementNFA;
    }

    @Override
    public NFA concat(NFA a) {
        NFAImpl concatNFA;

        Set<Character> concatChars = new HashSet<>();
        Set<Integer> concatAcceptingStates = new HashSet<>();
        int concatInitialState = this.getInitialState();

        //Concat's characters
        concatChars.addAll(a.getSymbols());
        concatChars.addAll(this.getSymbols());
        //Set accepting states to a's accepting states (increased by number of THIS states
        for (int i : a.getAcceptingStates()) {
            concatAcceptingStates.add(i+this.getNumStates());
        }

        concatNFA = new NFAImpl(a.getNumStates() + this.getNumStates(), concatChars, concatAcceptingStates, concatInitialState);

        //Adding transitions of THIS NFA to Concat NFA
        for (int i = 0; i < this.transitionList.size(); i++) {
            concatNFA.setTransition(this.transitionList.get(i).getFromState(), this.transitionList.get(i).getS(),this.transitionList.get(i).getToState());
        }

        Set<String>[][] transitionsA = a.getTransitions();
        for (int i = 0; i < transitionsA.length; i++) {
            for (int j = 0; j < transitionsA[i].length; j++) {
                if (transitionsA[i][j] != null) {
                    //Adding transitions
                    for (String s : transitionsA[i][j]) {
                        concatNFA.setTransition(i + this.getNumStates(), s, j + this.getNumStates());
                    }
                }
            }
        }

        //Epsilon Übergang für Concat, von Endzuständen von THIS NFA zu a.NFA
        for (int i : this.getAcceptingStates()) {
            concatNFA.setTransition(i, "", a.getInitialState()+this.getNumStates());
        }

        return concatNFA;
    }

    @Override
    public NFA kleeneStar() {
        NFAImpl kleeneNFA;

        Set<Integer> kleeneAcceptingStates = new HashSet<>();
        int kleeneInitialState = 0;
        Set<Integer> kleeneStatesSet = new HashSet<>();
        List<Transition> kleeneTransitionList = new ArrayList<>();

        //Füge neuen Startzustand hinzu
        this.statesSet.add(-1);

        //Erhöhe alle Zustände um 1 (wegen neuem Startzustand)
        for (int i : this.statesSet) {
            i++;
        }

        //Passe alle Zustände in den Transitionen an (+1)
        for (Transition t : transitionList) {
            t.setFromState(t.getFromState()+1);
            t.setToState(t.getToState()+1);
        }

        //Ergänze Transitionen mit Epsilon-Übergang von Endzuständen zu altem Startzustand
        for (int t : acceptingStates) {
            transitionList.add(new Transition(t+1, "", this.getInitialState()+1));
        }

        //Setze Endzustände neu (+1) und ergänze um neuen Startzustand
        /*
        todo
         */

        //kleeneNFA = new NFAImpl(this.getNumStates() + 1, this.getSymbols(), );

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
        if(acceptingStates.isEmpty()){
            return false;
        }
        int state = initialState;
        boolean transactionFound = false;
        boolean epsilonFound = true;

        for (int i = 0; i < w.length() || epsilonFound == true; i++) {
            transactionFound = false;
            //Prüfe ob Wortende erreicht, sonst suche weiter nach Übergängen
            if (!(i == w.length())) {
                for (int j = 0; j < transitionList.size() && transactionFound == false; j++) {
                    if (transitionList.get(j).getFromState() == state && transitionList.get(j).getS().equals(Character.toString(w.charAt(i))) ||
                            //Prüfe auch auf mögliche Epsilon Übergänge
                            transitionList.get(j).getFromState() == state && transitionList.get(j).getS().equals("")) {
                        state = transitionList.get(j).getToState();
                        transactionFound = true;
                    }
                }
                if (transactionFound == false) {
                    return accepts;
                }
            } else
            //Wenn Wort zu Ende oder leeres Wort als Eingabe, prüfe für weitere Epsilon Übergänge
            {
                while (epsilonFound) {
                    for (int j = 0; j < transitionList.size(); j++) {
                        if (transitionList.get(j).getFromState() == state && transitionList.get(j).getS().equals("")) {
                            state = transitionList.get(j).getToState();
                            //State changed, now set j=0 to iterate from 0 within transitionList again
                            j = 0;
                            if(acceptingStates.contains(state)){
                                //Sobald ein Endzustand erreicht, kann man beenden da leeres Wort
                                return true;
                            }
                        }
                        else
                            //Wenn kein Epsilon Übergang gefunden, beende weitere Suche
                            epsilonFound = false;
                    }
                }
            }
        }
        if (acceptingStates.contains(state)) {
            accepts = true;
        }
        return accepts;
    }

    @Override
    public Boolean acceptsNothing() {
        boolean acceptsNothing = true;
        for (int i = 0; i < transitionList.size() && acceptsNothing == false; i++) {
            if (acceptingStates.contains(transitionList.get(i).getToState()) && !(transitionList.get(i).getFromState() == transitionList.get(i).getToState())) {
                acceptsNothing = false;
            }
        }
        return acceptsNothing;
    }

    @Override
    public Boolean acceptsEpsilonOnly() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public Boolean acceptsEpsilon() {
        return this.accepts("");
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
