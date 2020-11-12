//
//  ContentView.swift
//  talan
//
//  Created by Miguel Panelo on 09/07/2020.
//  Copyright © 2020 Miguel Panelo. All rights reserved.
//

import SwiftUI
import SharedClient

struct ContentView: View {
    @State
    private var componentHolder =
        ComponentHolder {
            AWRootKt.AWRoot(componentContext: $0, appComponent: MainAppComponent())
    }
    
    var body: some View {
        RootView(componentHolder.component.model)
        .onAppear { LifecycleRegistryExtKt.resume(self.componentHolder.lifecycle) }
        .onDisappear { LifecycleRegistryExtKt.stop(self.componentHolder.lifecycle) }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

class ComponentHolder<T> {
    let lifecycle: LifecycleRegistry
    let component: T
    
    init(factory: (ComponentContext) -> T) {
        let lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        let component = factory(DefaultComponentContext(lifecycle: lifecycle))
        self.lifecycle = lifecycle
        self.component = component
        
        lifecycle.onCreate()
    }
    
    deinit {
        lifecycle.onPause()
    }
}
