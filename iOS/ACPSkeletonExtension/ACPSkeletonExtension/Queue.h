/*
 ADOBE CONFIDENTIAL
 
 Copyright 2019 Adobe
 All Rights Reserved.
 
 NOTICE: Adobe permits you to use, modify, and distribute this file in
 accordance with the terms of the Adobe license agreement accompanying
 it. If you have received this file from a source other than Adobe,
 then your use, modification, or distribution of it requires the prior
 written permission of Adobe.
 */

#ifndef Queue_h
#define Queue_h

#import <Foundation/Foundation.h>
#import "ACPExtensionEvent.h"

@interface Queue : NSObject

- (void)add:(nonnull ACPExtensionEvent*)event;
- (ACPExtensionEvent*) peek;
- (ACPExtensionEvent*) poll;
- (bool) hasNext;

@end

#endif /* Queue_h */