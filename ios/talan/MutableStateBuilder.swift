//
//  MutableStateBuilder.swift
//  ios-app
//
//  Created by Arkadii Ivanov on 9/14/20.
//  Copyright © 2020 Arkadii Ivanov. All rights reserved.
//

import Foundation
import SharedClient

func mutableValue<T: AnyObject>(_ initialValue: T) -> DecomposeMutableValue<T> {
    return MutableStateBuilderKt.mutableValue(initialValue: initialValue) as! DecomposeMutableValue<T>
}
