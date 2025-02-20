package Trees;

class AVLNode extends Node{
    protected int leftHeight, rightHeight;

    AVLNode(int data) {
        super(data);
        this.leftHeight = 0;
        this.rightHeight = 0;
    }

    public int getLeftHeight() {return this.leftHeight;}
    public int getRightHeight() {return this.rightHeight;}
    public int getHeight() {return Math.max(this.leftHeight, this.rightHeight) + 1;}

    // 삽입, 삭제 처럼 트리 모양에 변화가 생기는 상황에 계속 사용.
    public void updateHeight() {
        this.leftHeight = (getLeft() == null) ? 0 : Math.max(((AVLNode) getLeft()).leftHeight, ((AVLNode) getLeft()).rightHeight) + 1;
        this.rightHeight = (getRight() == null) ? 0 : Math.max( ((AVLNode) getRight()).leftHeight, ((AVLNode) getRight()).rightHeight) + 1;
    }
    
    // 트리의 균형을 확인할 때마다 사용
    public int getBalanceFactor() {
        updateHeight();
        return this.leftHeight - this.rightHeight;
    }

}