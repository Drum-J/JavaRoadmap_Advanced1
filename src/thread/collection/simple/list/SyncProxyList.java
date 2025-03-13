package thread.collection.simple.list;

public class SyncProxyList implements SimpleList {

    private SimpleList target; // 원본

    public SyncProxyList(SimpleList target) {
        this.target = target;
    }


    // 모든 메서드 synchronized, 원본 메서드(target.xxx()) 호출
    @Override
    public synchronized int size() {
        return target.size();
    }

    @Override
    public synchronized void add(Object e) {
        target.add(e);
    }

    @Override
    public synchronized Object get(int index) {
        return target.get(index);
    }

    @Override
    public synchronized String toString() {
        return target.toString() + " by " + this.getClass().getSimpleName();
    }
}
