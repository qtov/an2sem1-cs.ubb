package sem1;

public class SortingTask extends Task {
    private int[] arr;
    public SortingTask(String taskId, String descriere, int[] arrParam) {
        super(taskId, descriere);
        this.arr = arrParam.clone();
    }

    @Override
    public void execute() {
        boolean ok = false;
        while (!ok) {
            ok = true;
            for (int i = 0; i < this.arr.length - 1; i++) {
                if (this.arr[i] > this.arr[i + 1]) {
                    int aux = this.arr[i];
                    this.arr[i] = this.arr[i + 1];
                    this.arr[i + 1] = aux;
                    ok = false;
                }
            }
        }
        for (int i = 0; i < this.arr.length; i++) {
            System.out.print(this.arr[i]);
            System.out.print(" ");
        }
    }
}
