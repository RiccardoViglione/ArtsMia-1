package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
private ArtsmiaDAO dao;
private Map<Integer,ArtObject>idMap;

private Graph<ArtObject,DefaultWeightedEdge>grafo;
public Model() {
	dao=new ArtsmiaDAO();
	idMap=new HashMap<>();
	dao.listObjects(idMap);
	
}
public void creaGrafo() {
	grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
Graphs.addAllVertices(grafo, dao.getVertici(idMap));
for(Adiacenza a:this.dao.getArchi(idMap)) {
	Graphs.addEdgeWithVertices(this.grafo, a.getA1(), a.getA2(),a.getPeso());
	
}
}
public List<ArtObject> getVertici(){
	return new ArrayList<>(this.grafo.vertexSet());
}

public boolean grafoCreato() {
	if(this.grafo == null)
		return false;
	else 
		return true;
}

public int nVertici() {
	return this.grafo.vertexSet().size();
}

public int nArchi() {
	return this.grafo.edgeSet().size();
}
public int getConnessi(ArtObject o) {
ConnectivityInspector<ArtObject,DefaultWeightedEdge>ci=new ConnectivityInspector<ArtObject,DefaultWeightedEdge>(grafo);	
List<ArtObject >arte=new ArrayList<>(ci.connectedSetOf(o));
//arte.remove(o);
return arte.size();
	
}

}
