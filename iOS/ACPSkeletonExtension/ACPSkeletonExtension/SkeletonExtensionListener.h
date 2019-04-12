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

#ifndef SkeletonExtensionListener_h
#define SkeletonExtensionListener_h

#import "ACPExtensionEvent.h"
#import "ACPExtensionListener.h"

NS_ASSUME_NONNULL_BEGIN

@interface SkeletonExtensionListener : ACPExtensionListener

- (void) hear:(ACPExtensionEvent *)event;

@end

NS_ASSUME_NONNULL_END

#endif /* SkeletonExtensionListener_h */
