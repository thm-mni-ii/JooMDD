package de.thm.icampus.cjsl.generator;

import java.util.LinkedList;

import org.eclipse.emf.common.util.EList;

public class TreeStructur {
	
	public  int inkrementAll(TreeElement g, int rgt){
		g.setLft(rgt);
		g.setRgt(rgt+1);
		
		
		return rgt+2;
	}


	public  int inkrementLgt(TreeElement g, int rgt){
		g.setLft(rgt);	
		return rgt+1;
	}
	public  int inkrementRgt(TreeElement g, int rgt){
		g.setRgt(rgt);	
		return rgt+1;
	}
	public  LinkedList<TreeElement> searchAllkids(EList<TreeElement>groups, TreeElement parent){
		
		 LinkedList<TreeElement> result = new LinkedList<TreeElement>();
		
		if(groups == null)
		return result;
		
		for(TreeElement g: groups ){
			if(g.getParent() != -1){
				if(g.getParent() == parent.getId())
			      result.add(g);
			  
			  }
		}
		
		return result;
		
	}


	public  int buildthegroups(EList<TreeElement> groups, TreeElement parent,LinkedList<TreeElement> kids, int rgt, int index){
		
	if (kids.size() <= index)
		return rgt;
	
	return (searchAllkids(groups, kids.get(index)).isEmpty()==false) ? 
	 buildthegroups(groups,  parent,  kids ,  inkrementRgt(kids.get(index), buildthegroups(groups,  kids.get(index), searchAllkids(groups, kids.get(index)), inkrementLgt(kids.get(index), rgt), 0)), index +1 ) :
	 	buildthegroups(groups,  parent,  kids ,  inkrementAll(kids.get(index), rgt), index +1 ) ;
	}
	 

	

}
