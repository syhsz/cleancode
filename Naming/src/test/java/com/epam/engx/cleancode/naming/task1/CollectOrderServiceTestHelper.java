package com.epam.engx.cleancode.naming.task1;

import com.epam.engx.cleancode.naming.task1.thirdpartyjar.CollectionService;
import com.epam.engx.cleancode.naming.task1.thirdpartyjar.Submitable;

public class CollectOrderServiceTestHelper {

    public CollectOrderService getService(){
        return new CollectOrderService();
    }

    public void submit(Submitable collectOrderService) {
        ((CollectOrderService) collectOrderService).submitOrder(new OrderDummy());
    }

    public void setNotificationManager(NotificationManagerMock notificationManagerMock, Submitable collectOrderService) {
        ((CollectOrderService) collectOrderService).setSer2(notificationManagerMock);
    }

    public void setCollectionService(Submitable collectOrderService, CollectionService collectionServiceStub) {
        ((CollectOrderService) collectOrderService).setSer1(collectionServiceStub);
    }
}
