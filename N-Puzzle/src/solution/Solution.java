package solution;

import jigsaw.Jigsaw;
import jigsaw.JigsawNode;
import java.util.Queue;
import java.util.List;
import java.util.LinkedList;

/**
 * 在此类中填充算法，完成重拼图游戏（N-数码问题）
 */
public class Solution extends Jigsaw {
	private Queue<JigsawNode> willVisit;
    private Queue<JigsawNode> haveVisited;
	private int searchedNodesNum;
	private List<JigsawNode> solutionPath;
    
    /**
     * 拼图构造函数
     */
    public Solution() {
    	searchedNodesNum = 0;
    }

    /**
     * 拼图构造函数
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     */
    public Solution(JigsawNode bNode, JigsawNode eNode) {
        super(bNode, eNode);
    }

    /**
     *（实验一）广度优先搜索算法，求指定5*5拼图（24-数码问题）的最优解
     * 填充此函数，可在Solution类中添加其他函数，属性
     * @param bNode - 初始状态节点
     * @param eNode - 目标状态节点
     * @return 搜索成功时为true,失败为false
     */
    public boolean BFSearch(JigsawNode bNode, JigsawNode eNode) {
    	willVisit = new LinkedList<>();
        haveVisited = new LinkedList<>();

        this.beginJNode = bNode;
        this.endJNode = eNode;
        this.currentJNode = null;

        willVisit.add(this.beginJNode);

        while (!this.willVisit.isEmpty()) {
            this.searchedNodesNum++;
            this.currentJNode = this.willVisit.remove();
            this.haveVisited.add(currentJNode);

            if (this.currentJNode.equals(eNode)) {
                solutionPath = this.getPath();
                break;
            }

            JigsawNode[] nextNodes = new JigsawNode[]{
                new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode),
                new JigsawNode(this.currentJNode), new JigsawNode(this.currentJNode)
            };

            for (int i = 0; i < 4; i++) {
                if (nextNodes[i].move(i) && !this.haveVisited.contains(nextNodes[i])) {
                    this.willVisit.add(nextNodes[i]);
                }
            }
        }

        System.out.println("Jigsaw BF Search Result:");
        System.out.println("Begin state:" + this.getBeginJNode().toString());
        System.out.println("End state:" + this.getEndJNode().toString());
        // System.out.println("Solution Path: ");
        // System.out.println(this.solutionPath);
        System.out.println("Total number of searched nodes:" + this.searchedNodesNum);
        System.out.println("Depth of the current node is:" + this.getCurrentJNode().getNodeDepth());

        return this.isCompleted();
    }


    /**
     *（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)
     * 如 f(n) = s(n). s(n)代表后续节点不正确的数码个数
     * 此函数会改变该节点的estimatedValue属性值
     * 修改此函数，可在Solution类中添加其他函数，属性
     * @param jNode - 要计算代价估计值的节点
     */
    public void estimateValue(JigsawNode jNode) {
        int MahattenDistance = getMahattenDistance(jNode);
        int difference = getDifferenceBetweenEnd(jNode);
        int positionCorrectNum = getPositionCorrectNum(jNode);

        int estimate = MahattenDistance*2 + difference + positionCorrectNum;
        jNode.setEstimatedValue(estimate);    
    }
    
    private int getMahattenDistance(JigsawNode jNode) {
    	int MahattenDistance = 0, dimension = JigsawNode.getDimension();
    	
        for(int i = 1; i < dimension * dimension; i++) {
        	for(int j = 1; j < dimension * dimension; j++) {
        		if(jNode.getNodesState()[i] == endJNode.getNodesState()[j]
        				&& jNode.getNodesState()[i] != 0) {
        			int cX = (i - 1) / dimension;
        			int cY = (i + 4) % dimension;

        			int tX = (j - 1) / dimension;
        			int tY = (j + 4) % dimension;
        			
        			int dX = Math.abs(tX - cX);
        			int dY = Math.abs(tY - cY);
        			
        			MahattenDistance += (dX + dY);
        			break;
        		}        		
        	}
        }
    	
        return MahattenDistance;
    }
    
    private int getDifferenceBetweenEnd(JigsawNode jNode) {
    	int ret = 0, dimension = JigsawNode.getDimension();
    	for(int i = 1; i <= dimension*dimension; i++) {
    		if(endJNode.getNodesState()[i] == jNode.getNodesState()[i]) {
    			ret++;
    		}
    	}
    	
    	return ret;
    }
    
    private int getPositionCorrectNum(JigsawNode jNode) {
    	int ret = 0;
        int dimension = JigsawNode.getDimension();
        for (int i = 1; i < dimension * dimension; i++) {
            if (jNode.getNodesState()[i] + 1 != jNode.getNodesState()[i + 1]) {
                ret++;
            }
        }
        return ret;
    }
}
