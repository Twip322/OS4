
public class BlocksForFile {
	WorkPM PMemory;
	
	public BlocksForFile(WorkPM PMemory)
	{
		this.PMemory=PMemory;
	}
	public int[] getBlocks()
	{
		int startBlock=PMemory.getStartSelectedFile();
		int[] ans=new int[PMemory.getTables().size()];
		for(int i=0;i<ans.length;i++)
		{
			ans[i]=startBlock+i;
		}
		return ans;
	}
}
