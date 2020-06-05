
public class BlocksForFile {
	WorkPM PMemory;
	
	public int[] getBlocks(File file)
	{
		
		int startBlock=file.getStartInMem();
		int[] ans=new int[PMemory.getTables().size()];
		for(int i=0;i<ans.length;i++)
		{
			ans[i]=startBlock+i;
		}
		return ans;
	}
}
