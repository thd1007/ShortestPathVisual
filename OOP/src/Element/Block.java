package Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Block {
	public List<Vertex> listNode =  new ArrayList<>();
	public List<Edge> listEdge = new ArrayList<>();
	
	public void CopyListNode(List<Vertex> listV) {
		for(Vertex Vertex: listV) {
			listNode.add(Vertex.CopyVertex());
		}
	}
	// copy block 
	public Block CopyBlock() {
		Block newBlock = new Block(listEdge, listNode);
		return newBlock;
	}
	public Block(List<Edge> listE, List<Vertex> listV) {
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
		Collections.sort(listNode, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				// TODO Auto-generated method stub
				return (o1.getid() - o2.getid());
			}
		});
	}
	// check if Vertex exist in list node
	public boolean isVertexExist(Vertex V) {
		for(Vertex Vertex: listNode) {
			if(V.getid() == Vertex.getid()) {
				return true;
			}
		}
		return false;
	}
	// get start Vertex
	public Vertex startVertex() {
		if (Configuration.startVertex == null) return null;
		for(Vertex Vertex: listNode) {
			if(Vertex.getid() == Configuration.startVertex.getid()) {
				return Vertex;
			}
		}
		return null;
	}
	public Vertex endVertex() {
		if (Configuration.endVertex == null) return null;
		for(Vertex Vertex: listNode) {
			if(Vertex.getid() == Configuration.endVertex.getid()) {
				return Vertex;
			}
		}
		return null;
	}
}
