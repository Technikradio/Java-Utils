package org.technikradio.cf;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;

public class Api {
	
	private Hashtable<String, Function> functions;
	private Hashtable<String, ElementStack> words;
	private ElementStack mainWords;
	private PrintStream outPutStream;
	
	/**
	 * @return the words
	 */
	Hashtable<String, ElementStack> getWords() {
		return words;
	}

	/**
	 * @param words the words to set
	 */
	void setWords(Hashtable<String, ElementStack> words) {
		this.words = words;
	}

	private Api(){
		functions = new Hashtable<String, Function>();
	}

	public boolean contains(String part) {
		return functions.containsKey(part);
	}

	public boolean execute(String part, Stack s) {
		return functions.get(part).execute(s, this);
	}
	
	public static Api getNewInstance(){
		Api a = new Api();
		a.functions.put("+", new Function(){

			@Override
			public boolean execute(Stack stack, Api parent) {
				try{
					double a = stack.pull();
					double b = stack.pull();
					stack.push(a + b);
				} catch (NullPointerException e){
					return false;
				}
				return true;
			}});
		a.functions.put("-", new Function(){

			@Override
			public boolean execute(Stack stack, Api parent) {
				try{
					double b = stack.pull();
					double a = stack.pull();
					stack.push(a - b);
				} catch (NullPointerException e){
					return false;
				}
				return true;
			}});
		a.functions.put("*", new Function(){

			@Override
			public boolean execute(Stack stack, Api parent) {
				try{
					double a = stack.pull();
					double b = stack.pull();
					stack.push(a * b);
				} catch (NullPointerException e){
					return false;
				}
				return true;
			}});
		a.functions.put("/", new Function(){

			@Override
			public boolean execute(Stack stack, Api parent) {
				try{
					double b = stack.pull();
					double a = stack.pull();
					stack.push(a / b);
				} catch (NullPointerException e){
					return false;
				}
				return true;
			}});
		a.functions.put(".", new Function(){

			@Override
			public boolean execute(Stack stack, Api parent) {
				try{
					parent.getOutPutStream().print(' ');
					parent.getOutPutStream().print(stack.get());
				} catch (NullPointerException e){
					return false;
				}
				return true;
			}});
		a.functions.put("..", new Function(){

			@Override
			public boolean execute(Stack stack, Api parent) {
				try{
					parent.getOutPutStream().println("\n");
				} catch (NullPointerException e){
					return false;
				}
				return true;
			}});
		a.functions.put("[", new Function(){
			//TODO memory bug fixen
			@Override
			public boolean execute(Stack stack, Api parent) {
				String name;
				ArrayList<String> words = new ArrayList<String>();
				while(true){
					String nextWord = parent.getMainWords().nextElement();
					if(nextWord == "]"){
						break;
					}
					words.add(nextWord);
				}
				name = parent.getMainWords().nextElement();
				String[] _words = new String[words.size()];
				_words = words.toArray(_words);
				parent.getWords().put(name, new ElementStack(_words));
				return true;
			}});
		return a;
	}
	
	public boolean addFunction(Function f, String name){
		if(functions.contains(name))
			return false;
		functions.put(name, f);
		return true;
	}

	public PrintStream getOutPutStream() {
		return outPutStream;
	}

	public void setOutPutStream(PrintStream outPutStream) {
		this.outPutStream = outPutStream;
	}

	ElementStack getMainWords() {
		return mainWords;
	}

	void setMainWords(ElementStack mainWords) {
		this.mainWords = mainWords;
	}

}
