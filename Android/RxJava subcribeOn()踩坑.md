# 在正确的线程上使用Observable 

subscribeOn() 会标识这个代码块运行的线程,但是在observeOn()之后,剩余的代码运行的线程就是由observeOn()来决定的

所以这个例子:

只需要在第一个flatmap之前加一个subscribeon 规定这个和后面一个observable所在的线程就可以
最后在subscribe之前回调到主线程
````
sessionIdObservable
                .subscribeOn(Schedulers.newThread())
                .flatMap((Func1<Response, Observable<CardDataEtp>>) response ->
                {
                    //cookie 分为两个部分: 一个部分是 User-Agent 另外的一个部分是Cookie 和 wxqyuserid 组合起来的一个字段
                    //有点奇怪但是如果不是这样的形式没法请求成功
                    mCookieMap = new HashMap<>();
                    mCookieMap.put("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) AppleWebKit");
                    mConcatCookie = ASP_NET_SESSION + "=" + mSessionID + ";"
                            +WXQUERYID + "=" + App.sUser.sid;
                    mCookieMap.put("Cookie",mConcatCookie);
                    Observable<CardDataEtp> cardDataObservable = service.getCardData(mCookieMap);
                    return cardDataObservable;
                })
                .flatMap((Func1<Object, Observable<CardDailyUse>>) o -> {
                    CardDataEtp use = (CardDataEtp) o;
                    mCardDataEtp = use;


                    //这里的header可以和上面完全相同
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR));
                    Date today = calendar.getTime();
                    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) -7 );
                    Date seven = calendar.getTime();
                    String todayDays = DateUtil.toDateInYear(today);
                    String sevenDays = DateUtil.toDateInYear(seven);

                    Observable<CardDailyUse> cardDailyUseObservable = service.getCardDailyData(mCookieMap
                            ,1,100,sevenDays,todayDays);


                    return cardDailyUseObservable;
                })
                 .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() { }
                    @Override
                    public void onError(Throwable e) {e.printStackTrace();
                        Logger.d("card");}
                    @Override
                    public void onNext(Object o) {
                        CardDailyUse use = (CardDailyUse) o;
                        iCardView.initView(use,mCardDataEtp);
                    }
                });
````