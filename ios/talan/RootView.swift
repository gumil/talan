//
//  RootView.swift
//  talan
//
//  Created by Miguel Panelo on 04/11/2020.
//  Copyright © 2020 Miguel Panelo. All rights reserved.
//

import SwiftUI
import SharedClient

struct RootView: View {
    private let routerState: DecomposeValue<DecomposeRouterState<AnyObject, AWRootChild>>

    init(_ model: AWRootModel) {
        self.routerState = model.routerState
    }
    
    var body: some View {
        RouterView(self.routerState) { child, isHidden in
            if (child is AWRootChild.List) {
                AWListView((child as! AWRootChild.List).model)
                .isHidden(isHidden)
                .zIndex(1)
            }
        }
    }
}

//struct RootView_Previews: PreviewProvider {
//    static var previews: some View {
//        RootView(Model())
//    }
//
//    class Model : AWRootModel {
//        let routerState: Value<RouterState<AnyObject, TodoRootChild>> =
//            simpleRouterState(TodoRootChild.Main(model: MainView_Previews.Model()))
//                              //TodoRootChild.Edit(model: EditView_Previews.Model())
//    }
//}
