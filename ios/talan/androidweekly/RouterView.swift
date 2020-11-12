//
//  RouterView.swift
//  talan
//
//  Created by Miguel Panelo on 04/11/2020.
//  Copyright © 2020 Miguel Panelo. All rights reserved.
//

import SwiftUI
import SharedClient

struct RouterView<T : Hashable & AnyObject, Content : View> : View {
    @ObservedObject
    private var state: ObservableValue<RouterState<AnyObject, T>>
    private let render: (T, _ isHidden: Bool) -> Content

    init(_ routerState: Value<RouterState<AnyObject, T>>, @ViewBuilder render: @escaping (T, _ isHidden: Bool) -> Content) {
        self.state = ObservableValue(routerState)
        self.render = render
    }
    
    var body: some View {
        let routerState = self.state.value
        
        let backstack = routerState
            .backStack
            .compactMap { $0 as? RouterStateEntryCreated }
            .map { $0.component }
        
        return ZStack {
            ForEach(backstack, id: \.hashValue) {
                self.render($0, true)
            }

            self.render(routerState.activeChild.component, false)
        }
    }
}

func simpleRouterState<T : AnyObject>(_ child: T) -> Value<RouterState<AnyObject, T>> {
    return mutableValue(
        RouterState(
            activeChild: RouterStateEntryCreated(
                configuration: "config" as AnyObject,
                component: child
            ),
            backStack: []
        )
    )
}
