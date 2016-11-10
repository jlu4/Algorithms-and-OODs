import java.util.ArrayList;

class ETreeNode {
	int time;
	ArrayList<ETreeNode> bro;
	public ETreeNode(int time, ArrayList<ETreeNode> bro) {
		this.time = time;
		this.bro = bro;
	}
	public ETreeNode() {
		
	}
}
public class Employ {
	private static int max;
	private static ETreeNode et;
    public static int employ(ETreeNode a) {
    	if(a == null) return 0;
    	et = null;
    	max = 0;
    	Solution(a, 0, new int[2]);
    	return et.time;
    }
    public static int[] Solution(ETreeNode node, int start, int[] cul) {
    	if (node == null) return cul;
    	int sum = 0;
    	int num = 0;
        if(node.bro != null && node.bro.size() != 0) {
    	for (int i = 0; i < node.bro.size(); i++) {
    		
    		sum += Solution(node.bro.get(i), 1, cul)[0];
    		num += Solution(node.bro.get(i), 1, cul)[1];
    	}
    	}
    	sum = sum + node.time;   //remember
    	num++;
    	if(max < (sum / num) && num != 1) { max = sum / num; et = node; }
    	cul[0] = sum;
    	cul[1] = num;
    	return cul;
    	
    	
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ETreeNode a = new ETreeNode();
        ETreeNode b = new ETreeNode();
        ETreeNode c = new ETreeNode();
        ETreeNode d = new ETreeNode();
        ETreeNode e = new ETreeNode();
        ETreeNode f = new ETreeNode();
        ETreeNode g = new ETreeNode();
        ETreeNode h = new ETreeNode();
        ETreeNode i = new ETreeNode();
        ETreeNode j = new ETreeNode();
        ETreeNode k = new ETreeNode();
        ETreeNode l = new ETreeNode();
        ETreeNode m = new ETreeNode();
        ETreeNode o = new ETreeNode();
        ETreeNode p = new ETreeNode();
        ETreeNode q = new ETreeNode();
        ETreeNode r = new ETreeNode();
        a.time = 1;         b.time = 2;        c.time = 3;        d.time = 4;        e.time = 5;        f.time = 6;        g.time = 7;
        h.time = 8;        i.time = 9;        j.time = 10;        k.time = 11;        l.time = 12;        m.time = 13;
        o.time = 14;p.time = 15;q.time = 16;r.time = 17;
        ArrayList<ETreeNode> al = new ArrayList<ETreeNode>();
        al.add(b); al.add(c);al.add(d);
        ArrayList<ETreeNode> bl = new ArrayList<ETreeNode>();
        bl.add(e); bl.add(f);bl.add(g);
        ArrayList<ETreeNode> cl = new ArrayList<ETreeNode>();
        cl.add(h); cl.add(i);cl.add(j);
        ArrayList<ETreeNode> dl = new ArrayList<ETreeNode>();
        dl.add(k); dl.add(l);dl.add(m);
        ArrayList<ETreeNode> el = new ArrayList<ETreeNode>();
        el.add(o); el.add(p);
        ArrayList<ETreeNode> hl = new ArrayList<ETreeNode>();
        hl.add(q); hl.add(r);
        a.bro = al;   b.bro = bl; c.bro = cl; d.bro = dl;  e.bro = el; h.bro = hl;
        i.bro = null; j.bro = null;
        f.bro = null; g.bro = null; k.bro = null; l.bro = null; m.bro = null;
        o.bro = null; p.bro = null; q.bro = null; r.bro = null;
        System.out.println(employ(a));
        
	}
	/*if (node.bro == null || node.bro.size() == 0) return;
	sum = sum + node.time;
	for (int i = 0; i < node.bro.size(); i++){ 
     	sum = sum + node.bro.get(i).time;
	}
	int avg = sum / (node.bro.size() + 1);
	if (max < avg) {
		max = avg;
		et = node;
	} 
	for (int i = start; i < node.bro.size(); i++) {
	     DFS(node.bro.get(i), sum, i + 1);
	}
	return;*/
}
