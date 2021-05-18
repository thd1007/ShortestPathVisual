package OOPLab.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Block {
	public List<Vertext> listNode =  new ArrayList<>();
	public List<Edge> listEdge = new ArrayList<>();
	public void CopyListNode(List<Vertext> listV) {
		for(Vertext vertext: listV) {
			listNode.add(vertext.CopyVertext());
		}
	}
	// copy block 
	public Block CopyBlock() {
		Block newBlock = new Block(listEdge, listNode);
		return newBlock;
	}
	public Block(List<Edge> listE, List<Vertext> listV) {
		CopyListNode(listV);
		for(Edge edge: listE) {
			int S = edge.getStart().getid();
			int E = edge.getEnd().getid();
			Edge newEdge = edge.CopyEdge(listNode, S, E);
			listEdge.add(newEdge);
		}
	}
	// sort listNode by id
	public void sortListNodewithID() {
		Collections.sort(listNode, new Comparator<Vertext>() {

			@Override
			public int compare(Vertext o1, Vertext o2) {
				// TODO Auto-generated method stub
				return (o1.getid() - o2.getid());
			}
		});
	}
	// check if vertext exist in list node
	public boolean isVertextExist(Vertext V) {
		for(Vertext vertext: listNode) {
			if(V.getid() == vertext.getid()) {
				return true;
			}
		}
		return false;
	}
	// get start vertext
	public Vertext startVertext() {
		if (Configuration.startVertext == null) return null;
		for(Vertext vertext: listNode) {
			if(vertext.getid() == Configuration.startVertext.getid()) {
				return vertext;
			}
		}
		return null;
	}
	public Vertext endVertext() {
		if (Configuration.endVertext == null) return null;
		for(Vertext vertext: listNode) {
			if(vertext.getid() == Configuration.endVertext.getid()) {
				return vertext;
			}
		}
		return null;
	}
}
