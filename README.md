# EventBus
自己实现简单易用的事件通知及线程切换


#### 使用

    public class MainActivity extends BaseActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            EventBus.getDefault().register(this);
        }

        @Subscribe(threadMode = ThreadMode.MAIN)
        public void ttt(String str) {

        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            EventBus.getDefault().unRegister(this);
        }
    }
    
#### 在另外一个activity发起事件通知就可以了

    public void testClick(View v){
            EventBus.getDefault().post("aaaaaaaaa");
        }
        
#### 说明

@Subscribe(threadMode = ThreadMode.MAIN) 不设置这个参数默认是POSTING 和发起事件通知是同一个线程 ThreadMode.MAIN 不管发起事件通知是子线程还是主线程都切换至主线程  ThreadMode.BACKG 不管发起事件通知是子线程还是主线程都切换至子线程通知
