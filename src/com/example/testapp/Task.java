package com.example.testapp;

public class Task {
private boolean done;
private String toDo;
public Task(boolean l,String d){
	done=l;
	toDo=d;
}
public String getToDo() {
	return toDo;
}
public void setToDo(String toDo) {
	this.toDo = toDo;
}
public boolean getDone(){
	return done;
}
public void setDone(boolean l){
	done=l;
}
}
