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
    private var state: ObservableValue<DecomposeRouterState<AnyObject, T>>
    private let render: (T, _ isHidden: Bool) -> Content

    init(_ routerState: DecomposeValue<DecomposeRouterState<AnyObject, T>>, @ViewBuilder render: @escaping (T, _ isHidden: Bool) -> Content) {
        self.state = ObservableValue(routerState)
        self.render = render
    }
    
    var body: some View {
        let routerState = self.state.value
        
        let backstack =
            routerState
                .backStack
                .compactMap { $0 as? DecomposeRouterStateEntryCreated }
                .map { $0.component }
        
        return ZStack {
            ForEach(backstack, id: \.hashValue) {
                self.render($0, true)
            }

            self.render(routerState.activeChild.component, false)
        }
    }
}

func simpleRouterState<T : AnyObject>(_ child: T) -> DecomposeValue<DecomposeRouterState<AnyObject, T>> {
    return mutableValue(
        DecomposeRouterState(
            activeChild: DecomposeRouterStateEntryCreated(
                configuration: "config" as AnyObject,
                component: child
            ),
            backStack: []
        )
    )
}
